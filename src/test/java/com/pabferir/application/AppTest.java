package com.pabferir.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class AppTest {
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
