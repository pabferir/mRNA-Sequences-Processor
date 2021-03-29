package com.pabferir.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implements an application that
 * extracts mRNA sequences out of a given input.
 *
 * @author  Pablo Ferrando
 * @version 2.0
 * @since   2021-02-27
 */
public class App {

    private static ArrayList<String> geneList = new ArrayList<>();

    /**
     * Detects the specified processing method and triggers the mRNA sequences processing.
     * Once the processing is completed, triggers the generation of an output file with the genes obtained as result
     * of the processing.
     *
     * @param args The command line arguments provided by user to perform the processing.
     */
    public static void main(String[] args) {
        switch(args[0]) {
            case "-f":
                runWithInputFile(args[1]);
                break;
            default:
                runWithInputString(args);
                break;
        }
        generateOutputFile();
    }

    /**
     * Generates an input file with provided data and starts the processing.
     *
     * @param inputContent The data to be included in the file which will be used as processing source.
     */
    static void runWithInputString(String[] inputContent) {
        System.out.println("Creating input file with provided data...");
        try {
            File input = new File("./res/geneProc-input.txt");
            if (input.createNewFile()) {
                System.out.println("Input file " + input.getName() + " created with provided data.");
            } else {
                System.out.println("Input file " + input.getName() + " already exists.");
            }
        } catch(IOException e) {
            System.out.println("Could not create input file.");
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter("./res/geneProc-input.txt");
            for(int i=0; i<inputContent.length; i++) fileWriter.write(inputContent[i]);
            fileWriter.close();
            System.out.println("Successfully wrote data to input file.");
        } catch (IOException e) {
            System.out.println("Could not write data to input file.");
            e.printStackTrace();
        }
        try {
            File refmRNA = new File("./res/geneProc-input.txt");
            Scanner fileReader = new Scanner(refmRNA);

            processSequences(fileReader, geneList);

        } catch(IOException e) {
            System.out.println("Unable to load input file form path: ./res/geneProc-input.txt");
            e.printStackTrace();
        }
    }

    /**
     * Uses an already filled input file located in the res/ directory as data and starts the processing.
     *
     * @param fileName The of the file to be used from the res/ directory.
     */
    static void runWithInputFile(String fileName) {
        try {
            File refmRNA = new File("./res/" + fileName);
            Scanner fileReader = new Scanner(refmRNA);

            processSequences(fileReader, geneList);

        } catch(IOException e) {
            System.out.println("Unable to load input file form path: ./res/" + fileName);
            e.printStackTrace();
        }
    }

    /**
     * Extracts all complete genes out of an input file and stores them as Array in a List.
     *
     * @param fileReader The file containing the input data
     * @param geneList The list where complete genes will be stored
     */
    static void processSequences(Scanner fileReader, ArrayList<String> geneList) {
        System.out.println("\n=== Processing mRNA sequences... =====\n");

        ArrayList<String> currentGene = new ArrayList<>();
        String currentLine, first, second, third, currentCodon, unfinishedCodon;

        first = second = third = currentCodon = unfinishedCodon = "";
        int carry = 0;
        while(fileReader.hasNext()) {
            currentLine = fileReader.next().toUpperCase();

            //If there was an uncompleted codon in last line add it at the beginning
            if (carry!=0) {
                currentLine = unfinishedCodon.concat(currentLine);
                unfinishedCodon = "";
                carry = 0;
            }
            for(int i=0; i < currentLine.length(); i++) {
                first = String.valueOf(currentLine.charAt(i));
                //If current item in the file is a nucleotide get a possible codon and add it to current gene
                if (isNucleotide(first)) {
                    try {
                        second = String.valueOf(currentLine.charAt(++i)).toUpperCase();
                        third = String.valueOf(currentLine.charAt(++i)).toUpperCase();

                        currentCodon = first.concat(second).concat(third);
                        if (isNucleotide(second) && isNucleotide(third)) {
                            currentGene.add(currentCodon);
                            //If its a stop codon and out of noise section add gene to gene list and start another gene
                            if (isStopCodon(currentCodon) && currentGene.size() != 1) {
                                geneList.add(currentGene.toString());
                                currentGene.clear();
                            }
                        } else {
                            //Not a valid codon
                            System.out.println(" > Invalid codon " + currentCodon + " detected.");
                        }
                        //Clear current codon
                        currentCodon = first = second = third = "";
                    } catch (IndexOutOfBoundsException e) {
                        //Save uncompleted codon for next iteration
                        carry = currentLine.length() % 3;
                        switch (carry) {
                            case 1:
                                unfinishedCodon = first;
                                break;
                            case 2:
                                unfinishedCodon = first.concat(second);
                                break;
                        }
                        break;
                    }
                } else {
                    //Not a valid nucleotide
                    System.out.println(" > Invalid nucleotide " + first + " detected.");
                }

            }
        }

        System.out.println("\n=== Processing completed =============\n");
    }

    /**
     * Generates an output file with all the genes obtained in JSON format as result of the input processing.
     */
    private static void generateOutputFile() {
        try {
            File output = new File("./res/geneProc-output.txt");
            if (output.createNewFile()) {
                System.out.println("Output file created: " + output.getName());
            } else {
                System.out.println("Output file already exists.");
            }
        } catch (IOException e) {
            System.out.println("Could not create output file.");
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter("./res/geneProc-output.txt");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            fileWriter.write(gson.toJson(geneList));
            fileWriter.close();
            //No genes obtained
            if(geneList.isEmpty()) {
                System.out.println("\n" +
                        "*** WARNING *************************************************************\n" +
                        "* No complete genes detected.                                           *\n" +
                        "* Make sure your input provided an actual gene ended with a stop codon. *\n" +
                        "*************************************************************************\n");
            } else {
                System.out.println("Successfully wrote to output file.");
            }
        } catch (IOException e) {
            System.out.println("Could not write to output file.");
            e.printStackTrace();
        }
    }

    /**
     * Determines whether an input string is actually a stop codon.
     *
     * @param codon The string which is a possible stop codon
     * @return true if the item is an actual stop codon, false otherwise.
     */
    static boolean isStopCodon(String codon) {
        return (codon.equals("UAG") || codon.equals("UGA") || codon.equals("UAA"));
    }

    /**
     * Determines whether an input item is actually a nucleotide.
     *
     * @param nucleotide The item which is a possible nucleotide
     * @return true if the item is an actual nucleotide, false otherwise.
     */
    static boolean isNucleotide(String nucleotide) {
        return (nucleotide.equals("A") || nucleotide.equals("U") || nucleotide.equals("G") || nucleotide.equals("C"));
    }
}
