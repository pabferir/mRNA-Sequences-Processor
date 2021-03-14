# mRNA Sequences Processor

![Build and Test main](https://github.com/pabferir/mRNA-Sequences-Processor/actions/workflows/gradle.yml/badge.svg)

This is my solution for the Code Challenge 1 by the ADCenter Network from Capgemini on processing mRNA sequences. A more
detailed explanation on the challenge [can be found here]((doc/ADCenter_Network_Code_Challenge_Processing_mRNA_Sequences.pdf)).

## Technologies
This solution is written in Java and uses Gradle for build automation.

### Why Java?
![Java logo](doc/java-logo.png | height=175)
Java is the programming language I am most experienced with and therefore the one with which I feel most comfortable.

### Why Gradle?
![Gradle logo](doc/gradle-logo.png | height=150)
[About 48% of Java developers use Gradle](https://www.jetbrains.com/lp/devecosystem-2020/java/). Gradle is faster and in 
my opinion provides a better and easier experience than Maven, which makes it a good option for this challenge.

## Prerequisites
You need to have [Gradle](https://gradle.org/install/) installed in order to build this project.

## Usage

To compile and build the project run the following command on the root directory:
```
> gradle build
```
To run the tests, use the command:
```
> gradle test
```
To run the program, use the command:
```
> gradle run
```

### Output
Once you run the program, you will find the results in the `geneProc-output.txt` file generated within the `res/` directory.

## License

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This project is licensed under the Apache-2.0 License - see the [LICENSE](LICENSE) file for details.
Copyright © 2021 Pablo Ferrando Iranzo
