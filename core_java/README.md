# Core Java Apps
This project consists of three Java applications below

1. [Java Grep App](./grep)

# Introduction 
The Java Grep App is designed to mimic the behaviour of the grep command. It takes a given regex pattern and then takes all the lines from all the text files in the current directory and subdirectories and outputs them to an output file. I wrote the app in Java. I wrote all the code using the intelliJ IDE. I used Maven for dependency management. I used SLF4J for logging errors. I used Stream libraries like java.util.stream.Collectors and java.util.stream.Stream to optimize the app by improving memory efficiency. I then finally used docker to package the app and it's dependencies into a docker image. 

# Quick Start
The app can be ran using three difference methods.

    1. Using the jar file
    
    After compiling the app using maven a jar file is generated called grep-1.0-SNAPSHOT.jar and with this file we can simply run the following line with the appropriate arguements
    java -jar grep-1.0-SNAPSHOT.jar "regex pattern" "source directory" "outfile" 

    2. Downloading and running the docker image

    You can pull the docker image from dockerhub using the command dockerpull vgill16/grep and then run the following line while substituting in the appropriate arguments.

    docker run --rm \ -v `pwd`/data:/data -v `pwd`/log:/log \ vgill16/grep "regex pattern" "source directory" "outfile" 

# Implementation
Below is the pseudocode for the basic flow of the application. Essentially the app works by building a list of files recursively starting from the designated source directory and then reading all the lines in each file and adding a specific line to the outfile if it matches the provided regex. 
## Pseudocode

```
matchedLines[]
for file in listFilesRecursively(rootDir)
    for line in readFile(file)
        if containsPattern(line)
            matchedLines.add(line)
writeToFile(matchedLines)
```
## Performance Issue
The original design of the application (that doesn't use Streams) runs into memory issues when dealing with large files due to the fact that it stores all the lines in memory. The new implementation of the app (which extends the original JavaGrepImp) solves this issue by using Streams. 

# Test
I first tested the application by using the IDE and manually inputting the arguements to insure proper output. After that I performed both of the methods listed under the quickstart section to make sure that those methods yeilded the same results. During the testing process I used logger to handle errors.

#Deployment
To deploy the app I turned the app into a docker image and uploaded it to my dockerhub account. To run the application follow the instructions under the Quick Start section.

#Improvement
One improvement I can make is to optimize memory usage by changing the methods listFiles and readLines to return Streams instead of lists.

3. [JDBC App](./jdbc)
4. [Twitter CRUD App](./twitter)
