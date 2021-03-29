package com.pabferir.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AppTest {

    @Test
    void processSequences_InputIsEmpty_0() {
        String data = "";
        ArrayList<String> geneList = new ArrayList<>();
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner fileReader = new Scanner(System.in);
            App.processSequences(fileReader, geneList);
        } catch(Exception e) {
            e.printStackTrace();
        }

        Assertions.assertAll(
                () -> Assertions.assertEquals(0, geneList.size()),
                () -> Assertions.assertNotEquals(1, geneList.size()),
                () -> Assertions.assertNotEquals(2, geneList.size())
        );
    }

    @Test
    void processSequences_InputContainsDataButNoExpectedCodonsSize_0() {
        String data = "aaucaauuagu";
        ArrayList<String> geneList = new ArrayList<>();
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner fileReader = new Scanner(System.in);
            App.processSequences(fileReader, geneList);
        } catch(Exception e) {
            e.printStackTrace();
        }

        Assertions.assertAll(
                () -> Assertions.assertEquals(0, geneList.size()),
                () -> Assertions.assertNotEquals(1, geneList.size()),
                () -> Assertions.assertNotEquals(2, geneList.size())
        );
    }

    @Test
    void processSequences_InputContainsCodonsButNoCompleteGenes_0() {
        String data = "aauaauaau";
        ArrayList<String> geneList = new ArrayList<>();
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner fileReader = new Scanner(System.in);
            App.processSequences(fileReader, geneList);
        } catch(Exception e) {
            e.printStackTrace();
        }

        Assertions.assertAll(
                () -> Assertions.assertEquals(0, geneList.size()),
                () -> Assertions.assertNotEquals(1, geneList.size()),
                () -> Assertions.assertNotEquals(2, geneList.size())
        );
    }

    @Test
    void processSequences_InputContainsOneGeneButInvalidatedByInvalidData_0() {
        String data = "aa > N M1 23 u  u ag uu";
        ArrayList<String> geneList = new ArrayList<>();
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner fileReader = new Scanner(System.in);
            App.processSequences(fileReader, geneList);
        } catch(Exception e) {
            e.printStackTrace();
        }

        Assertions.assertAll(
                () -> Assertions.assertEquals(0, geneList.size()),
                () -> Assertions.assertNotEquals(1, geneList.size()),
                () -> Assertions.assertNotEquals(2, geneList.size())
        );
    }

    @Test
    void processSequences_InputContainsTwoGenesButInvalidatedByInvalidData_0() {
        String data = "aa > N M1 23 u u ag aa > N M1 23 u u ag uu";
        ArrayList<String> geneList = new ArrayList<>();
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner fileReader = new Scanner(System.in);
            App.processSequences(fileReader, geneList);
        } catch(Exception e) {
            e.printStackTrace();
        }

        Assertions.assertAll(
                () -> Assertions.assertEquals(0, geneList.size()),
                () -> Assertions.assertNotEquals(1, geneList.size()),
                () -> Assertions.assertNotEquals(2, geneList.size())
        );
    }

    @Test
    void processSequences_InputContainsOneGene_1() {
        String data = "aauuag";
        ArrayList<String> geneList = new ArrayList<>();
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner fileReader = new Scanner(System.in);
            App.processSequences(fileReader, geneList);
        } catch(Exception e) {
            e.printStackTrace();
        }

        Assertions.assertAll(
                () -> Assertions.assertNotEquals(0, geneList.size()),
                () -> Assertions.assertEquals(1, geneList.size()),
                () -> Assertions.assertNotEquals(2, geneList.size())
        );
    }

    @Test
    void processSequences_InputContainsOneCompleteGeneAndOneIncompleteGene_1() {
        String data = "aauuaguuuaaacccggg";
        ArrayList<String> geneList = new ArrayList<>();
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner fileReader = new Scanner(System.in);
            App.processSequences(fileReader, geneList);
        } catch(Exception e) {
            e.printStackTrace();
        }

        Assertions.assertAll(
                () -> Assertions.assertNotEquals(0, geneList.size()),
                () -> Assertions.assertEquals(1, geneList.size()),
                () -> Assertions.assertNotEquals(2, geneList.size())
        );
    }

    @Test
    void processSequences_InputContainsOneGeneAndInvalidDataBetweenCodons_1() {
        String data = "a au > N M1 23 u ag uu";
        ArrayList<String> geneList = new ArrayList<>();
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner fileReader = new Scanner(System.in);
            App.processSequences(fileReader, geneList);
        } catch(Exception e) {
            e.printStackTrace();
        }

        Assertions.assertAll(
                () -> Assertions.assertNotEquals(0, geneList.size()),
                () -> Assertions.assertEquals(1, geneList.size()),
                () -> Assertions.assertNotEquals(2, geneList.size())
        );
    }

    @Test
    void processSequences_InputContainsOneGeneInvalidatedByInvalidDataAndOneValidGene_1() {
        String data = "aa > N M1 23 u  u ag uu uag";
        ArrayList<String> geneList = new ArrayList<>();
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner fileReader = new Scanner(System.in);
            App.processSequences(fileReader, geneList);
        } catch(Exception e) {
            e.printStackTrace();
        }

        Assertions.assertAll(
                () -> Assertions.assertNotEquals(0, geneList.size()),
                () -> Assertions.assertEquals(1, geneList.size()),
                () -> Assertions.assertNotEquals(2, geneList.size())
        );
    }

    @Test
    void processSequences_InputContainsTwoGene_2() {
        String data = "aauuagaauuag";
        ArrayList<String> geneList = new ArrayList<>();
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner fileReader = new Scanner(System.in);
            App.processSequences(fileReader, geneList);
        } catch(Exception e) {
            e.printStackTrace();
        }

        Assertions.assertAll(
                () -> Assertions.assertNotEquals(0, geneList.size()),
                () -> Assertions.assertNotEquals(1, geneList.size()),
                () -> Assertions.assertEquals(2, geneList.size())
        );
    }

    @Test
    void processSequences_InputContainsTwoCompleteGenesAndOneIncompleteGene_2() {
        String data = "aauuagaauuaguuuaaacccggg";
        ArrayList<String> geneList = new ArrayList<>();
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner fileReader = new Scanner(System.in);
            App.processSequences(fileReader, geneList);
        } catch(Exception e) {
            e.printStackTrace();
        }

        Assertions.assertAll(
                () -> Assertions.assertNotEquals(0, geneList.size()),
                () -> Assertions.assertNotEquals(1, geneList.size()),
                () -> Assertions.assertEquals(2, geneList.size())
        );
    }

    @Test
    void processSequences_InputContainsTwoGenesAndInvalidDataBetweenCodons_2() {
        String data = "a au > N M1 23 u ag a au > N M1 23 u ag uu";
        ArrayList<String> geneList = new ArrayList<>();
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner fileReader = new Scanner(System.in);
            App.processSequences(fileReader, geneList);
        } catch(Exception e) {
            e.printStackTrace();
        }

        Assertions.assertAll(
                () -> Assertions.assertNotEquals(0, geneList.size()),
                () -> Assertions.assertNotEquals(1, geneList.size()),
                () -> Assertions.assertEquals(2, geneList.size())
        );
    }

    @Test
    void processSequences_InputContainsOneGeneInvalidatedByInvalidDataAndTwoValidGenes_2() {
        String data = "aa > N M1 23 u  u ag uu uag  u  u ag uu uag";
        ArrayList<String> geneList = new ArrayList<>();
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner fileReader = new Scanner(System.in);
            App.processSequences(fileReader, geneList);
        } catch(Exception e) {
            e.printStackTrace();
        }

        Assertions.assertAll(
                () -> Assertions.assertNotEquals(0, geneList.size()),
                () -> Assertions.assertNotEquals(1, geneList.size()),
                () -> Assertions.assertEquals(2, geneList.size())
        );
    }

    @Test
    void isNucleotide_ValidNucleotideFormat_True() {
        List<String> nucleotides = List.of("A", "C", "G", "U");

        Assertions.assertAll(
                () -> Assertions.assertTrue(App.isNucleotide(nucleotides.get(0))),
                () -> Assertions.assertTrue(App.isNucleotide(nucleotides.get(1))),
                () -> Assertions.assertTrue(App.isNucleotide(nucleotides.get(2))),
                () -> Assertions.assertTrue(App.isNucleotide(nucleotides.get(3)))
        );
    }

    @Test
    void isNucleotide_InvalidNucleotideFormat_False() {
        List<String> nucleotides = List.of("a", "c", "g", "u");

        Assertions.assertAll(
                () -> Assertions.assertFalse(App.isNucleotide(nucleotides.get(0))),
                () -> Assertions.assertFalse(App.isNucleotide(nucleotides.get(1))),
                () -> Assertions.assertFalse(App.isNucleotide(nucleotides.get(2))),
                () -> Assertions.assertFalse(App.isNucleotide(nucleotides.get(3)))
        );
    }

    @Test
    void isNucleotide_InvalidAlphanumeric_False() {
        List<String> invalidAlphanumerics = List.of(" ", ">", "n", "N", "8", "1");

        Assertions.assertAll(
                () -> Assertions.assertFalse(App.isNucleotide(invalidAlphanumerics.get(0))),
                () -> Assertions.assertFalse(App.isNucleotide(invalidAlphanumerics.get(1))),
                () -> Assertions.assertFalse(App.isNucleotide(invalidAlphanumerics.get(2))),
                () -> Assertions.assertFalse(App.isNucleotide(invalidAlphanumerics.get(3))),
                () -> Assertions.assertFalse(App.isNucleotide(invalidAlphanumerics.get(4))),
                () -> Assertions.assertFalse(App.isNucleotide(invalidAlphanumerics.get(5)))
        );
    }

    @Test
    void isStopCodon_ValidStopCodon_True() {
        List<String> stopCodons = List.of("UAG", "UGA", "UAA");

        Assertions.assertAll(
                () -> Assertions.assertTrue(App.isStopCodon(stopCodons.get(0))),
                () -> Assertions.assertTrue(App.isStopCodon(stopCodons.get(1))),
                () -> Assertions.assertTrue(App.isStopCodon(stopCodons.get(2)))
        );
    }

    @Test
    void isStopCodon_RandomNotStopCodon_False() {
        List<String> randomCodons = List.of("AUG", "UUG", "GAA", "UAU", "GUG", "CCU", "ACC", "GCA");

        Assertions.assertAll(
                () -> Assertions.assertFalse(App.isStopCodon(randomCodons.get(0))),
                () -> Assertions.assertFalse(App.isStopCodon(randomCodons.get(1))),
                () -> Assertions.assertFalse(App.isStopCodon(randomCodons.get(2))),
                () -> Assertions.assertFalse(App.isStopCodon(randomCodons.get(3))),
                () -> Assertions.assertFalse(App.isStopCodon(randomCodons.get(4))),
                () -> Assertions.assertFalse(App.isStopCodon(randomCodons.get(5))),
                () -> Assertions.assertFalse(App.isStopCodon(randomCodons.get(6))),
                () -> Assertions.assertFalse(App.isStopCodon(randomCodons.get(7)))
        );
    }
}
