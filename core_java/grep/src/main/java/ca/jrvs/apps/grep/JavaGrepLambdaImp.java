package ca.jrvs.apps.grep;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp{

    public static void main(String[] args) {
        if (args.length == 3){

            //creating JavaGrepLambdaImp instead of JavaGrepImp
            //JavaGrepLambdaImp inherits all methods from JavaGrepImp except
            //it overrides readLines and listFiles

            JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
            javaGrepLambdaImp.setRegex(args[0]);
            javaGrepLambdaImp.setRootPath(args[1]);
            javaGrepLambdaImp.setOutFile(args[2]);

            try {
                //calling parent method,
                //but it will call override method (in this class)
                javaGrepLambdaImp.process();
            } catch (Exception ex) {
                // I made JavaGrepImp.logger static to make this line work but this
                // may cause problem so I need to check this later to make sure
                logger.error("Some error happened");
            }
        }
        else {
            throw new IllegalArgumentException("Usage: JavaGrep regex rootPath outFile");
        }
    }

    @Override
    public List<String> readLines(File inputFile){
        List<String> result = new ArrayList<>();
        try (Stream<String> stream = Files.lines(inputFile.toPath())){
            result = stream.collect(Collectors.toList());
        } catch (Exception ex) {
            logger.error("There was an error in readLines so fix it");
        }
        return result;
    }

    @Override
    public List<File> listFiles(String rootDir){
        return java.util.Arrays.stream(new File(rootDir).listFiles())
                .flatMap(file -> file.isDirectory() ? listFiles(file.getAbsolutePath()).stream() : java.util.stream.Stream.of(file))
                .collect(Collectors.toList());
    }

}
