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

    You can pull the docker image from dockerhub using dockerpull vgill16/grep. You then run the following line while substituting in the appropriate arguments.

    docker run --rm \ -v `pwd`/data:/data -v `pwd`/log:/log \ vgill16/grep "regex pattern" "source directory" "outfile" 

# Implementation
## Pseudocode

  

3. [JDBC App](./jdbc)
4. [Twitter CRUD App](./twitter)
