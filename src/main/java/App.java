import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        ArrayList<String> geneList = new ArrayList<>();

        try {
            File refmRNA = new File("./res/refMrna.fa.corrected.txt");
            Scanner fileReader = new Scanner(refmRNA);

            processSequencies(fileReader, geneList);
            System.out.println(geneList.toString());

        } catch(IOException e) {
            System.out.println("Unable to load file form path: ./res/refMrna.fa.corrected.txt");
            e.printStackTrace();
        }
    }

    public static void processSequencies(Scanner fileReader, ArrayList<String> geneList) {
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
                            System.out.println("Invalid codon " + currentCodon + " detected.");
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
                    System.out.println("Invalid nucleotide " + first + " detected.");
                }

            }
        }
    }

    public static boolean isStopCodon(String codon) {
        return (codon.equals("UAG") || codon.equals("UGA") || codon.equals("UAA"));
    }

    public static boolean isNucleotide(String nucleotide) {
        return (nucleotide.equals("A") || nucleotide.equals("U") || nucleotide.equals("G") || nucleotide.equals("C"));
    }
}
