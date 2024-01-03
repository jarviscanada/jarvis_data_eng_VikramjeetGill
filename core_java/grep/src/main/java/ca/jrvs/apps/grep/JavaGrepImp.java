package ca.jrvs.apps.grep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.BasicConfigurator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaGrepImp implements JavaGrep, LambdaStreamExc {

    static final Logger logger = LoggerFactory.getLogger(JavaGrep.class);
    private String regex;
    private String rootPath;
    private String outFile;

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    @Override
    public Stream<String> createStrStream(String... strings) {
        return Arrays.stream(strings);
    }

    @Override
    public Stream<String> toUpperCase(String... strings) {
        return createStrStream(strings).map(String::toUpperCase);
    }

    @Override
    public Stream<String> filter(Stream<String> stringStream, String pattern) {
        return stringStream.filter(s -> !s.contains(pattern));
    }

    @Override
    public IntStream createIntStream(int[] arr) {
        return Arrays.stream(arr);
    }

    @Override
    public <E> List<E> toList(Stream<E> stream) {
        return stream.collect(Collectors.toList());
    }

    @Override
    public List<Integer> toList(IntStream intStream) {
        return intStream.boxed().collect(Collectors.toList());
    }

    @Override
    public IntStream createIntStream(int start, int end) {
        return IntStream.rangeClosed(start, end);
    }

    @Override
    public DoubleStream squareRootIntStream(IntStream intStream) {
        return intStream.mapToDouble(Math::sqrt);
    }

    @Override
    public IntStream getOdd(IntStream intStream) {
        return intStream.filter(n -> n % 2 != 0);
    }

    @Override
    public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
        return message -> System.out.println(prefix + message + suffix);
    }

    @Override
    public void printMessages(String[] messages, Consumer<String> printer) {
        Arrays.stream(messages).forEach(printer);
    }

    @Override
    public void printOdd(IntStream intStream, Consumer<String> printer) {
        getOdd(intStream).mapToObj(String::valueOf).forEach(printer);
    }

    @Override
    public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
        // Using flatMap
        return ints.flatMap(List::stream);
    }

    public static void main(String[] args) {
        if(args.length != 3) {
            throw new IllegalArgumentException("Usage: JavaGrep regex rootPath outFile");
        }

        //Use default logger config
        BasicConfigurator.configure();

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try {
            javaGrepImp.process();
        }catch (Exception ex) {
            // I had to remove the javaGrepImp. part of the logger statement since
            // I changed it to be static to solve a problem in JavaGrepLambdaImp
            logger.error("Error: Unable to process", ex);
        }
    }

    @Override
    public void process() throws IOException {
        List<String> matchedLines = new ArrayList<>();
        for(File file : listFiles(getRootPath())){
            List<String> list = readLines(file);
            for(String line : list){
                if(containsPattern(line)){
                    matchedLines.add(line);
                }
            }
        }
        writeToFile(matchedLines);
    }

    // Might need to handle the exception if there are problems
    @Override
    public List<File> listFiles(String rootDir) throws IllegalArgumentException{
        List<File> result = new ArrayList<>();
        File root = new File(rootDir);
        // Below is the base case. This base case returns if the root doesn't exist or isn't a directory
        if (!root.exists() || !root.isDirectory()) {
            return result;
        }
        // Add all files in the root directory to the list files
        File[] files = root.listFiles();
        if (files != null) {
            for (File file : files) {
                // If the file isn't a directory then add it to result but if it is a
                // directory then simply recursively call listFile() on it.
                if (file.isFile()){
                    result.add(file);
                }
                else if (file.isDirectory()) {
                    // Recursively traverse directories and add files
                    result.addAll(listFiles(file.getAbsolutePath()));
                }
            }
        }
        return result;
    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> result = new ArrayList<>();
        // The reason I'm using a try catch block is to avoid having to put "throws IOException" in
        // the signature since the method in the interface doesn't have that
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line); // Add each line to the list
            }
        } catch (IOException e) {
            logger.debug("There was an IOException in the readLines method");
        }
        return result;
    }

    // containsPattern checks to see if the given String line matches the provided regex pattern,
    // and it returns the boolean result.
    @Override
    public boolean containsPattern(String line) {
        boolean result = line.matches(getRegex());
        return result;
    }

    // writeToFile takes the lines (represented as Strings) from the provided list and writes
    // them to outFile. Technically using BufferedWriter would be more efficient, but it's not
    // a big deal.
    @Override
    public void writeToFile(List<String> lines) throws IOException {
        try {
            FileWriter myWriter = new FileWriter(getOutFile());
            for(String line : lines){
                myWriter.write(line);
            }
            myWriter.close();
        } catch (IOException e) {
            logger.debug("There was an IOException in the writeToFile method");
        }
    }
}

