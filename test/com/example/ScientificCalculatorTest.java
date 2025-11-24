package com.example;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;



public class ScientificCalculatorTest {

   


    ScientificCalculator calc = new ScientificCalculator();

    @Test
    void testBuggy() {
        assertEquals(27, calc.buggy(10));

    }


    // ---------------- BASIC ARITHMETIC ---------------- //

    @Test
    void testAdd() {
        assertEquals(7.5, calc.add(5.5, 2.0));
    }

    @Test
    void testSubtract() {
        assertEquals(1.5, calc.subtract(5.5, 4.0));
    }

    @Test
    void testMultiply() {
        assertEquals(12.0, calc.multiply(3, 4));
    }

    @Test
    void testDivide() {
        assertEquals(2.5, calc.divide(5, 2));
    }

    @Test
    void testDivideByZeroThrows() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }

    // ---------------- SCIENTIFIC FUNCTIONS ---------------- //

    @Test
    void testPow() {
        assertEquals(27.0, calc.pow(3, 3));
    }

    @Test
    void testSqrt() {
        assertEquals(5.0, calc.sqrt(25));
    }

    @Test
    void testSqrtNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.sqrt(-4));
    }

    @Test
    void testNthRoot() {
        assertEquals(4.0, calc.nthRoot(64, 3), 0.0001);
    }

    @Test
    void testNthRootEvenNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.nthRoot(-27, 2));
    }

    @Test
    void testFactorial() {
        assertEquals(720, calc.factorial(6));
    }

    @Test
    void testFactorialInvalidThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(25));
    }

    @Test
    void testSinDeg() {
        assertEquals(0.0, calc.sinDeg(0), 0.0001);
        assertEquals(1.0, calc.sinDeg(90), 0.0001);
    }

    @Test
    void testCosDeg() {
        assertEquals(1.0, calc.cosDeg(0), 0.0001);
    }

    @Test
    void testTanDeg() {
        assertEquals(0.0, calc.tanDeg(0), 0.0001);
    }

    @Test
    void testLog10() {
        assertEquals(2.0, calc.log10(100));
    }

    @Test
    void testLog10InvalidThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.log10(0));
    }

    @Test
    void testLn() {
        assertEquals(1.0, calc.ln(Math.E), 0.0001);
    }

    @Test
    void testLnInvalidThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.ln(-5));
    }

    // ---------------- STATISTICS ---------------- //

    @Test
    void testMean() {
        assertEquals(3.0, calc.mean(new double[]{1, 3, 5}));
    }

    @Test
    void testMedianOdd() {
        assertEquals(3.0, calc.median(new double[]{1, 3, 5}));
    }

    @Test
    void testMedianEven() {
        assertEquals(2.5, calc.median(new double[]{1, 2, 3, 4}));
    }

    @Test
    void testVariance() {
        assertEquals(2.6666666666666665, calc.variance(new double[]{1, 3, 5}));
    }

    @Test
    void testStdDev() {
        assertEquals(Math.sqrt(2.6666666666666665), calc.stdDev(new double[]{1, 3, 5}));
    }

    @Test
    void testStatsInvalidThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.mean(new double[]{}));
    }

    // ---------------- DATE OPERATIONS ---------------- //

    @Test
    void testAddDays() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        assertEquals(LocalDate.of(2024, 1, 11), calc.addDays(date, 10));
    }

    @Test
    void testDiffInDays() {
        LocalDate d1 = LocalDate.of(2024, 1, 1);
        LocalDate d2 = LocalDate.of(2024, 1, 31);
        assertEquals(30, calc.diffInDays(d1, d2));
    }

    @Test
    void testIsLeapYear() {
        assertTrue(calc.isLeapYear(2024));
        assertFalse(calc.isLeapYear(2023));
    }

    // ---------------- BINARY SEARCH ---------------- //

    @Test
    void testBinarySearchIterativeFound() {
        double[] arr = {1, 2, 3, 4, 5};
        assertEquals(2, calc.binarySearchIterative(arr, 3));
    }

    @Test
    void testBinarySearchIterativeNotFound() {
        double[] arr = {1, 2, 3};
        assertEquals(-1, calc.binarySearchIterative(arr, 10));
    }

    @Test
    void testBinarySearchRecursiveFound() {
        double[] arr = {1, 2, 3, 4, 5};
        assertEquals(4, calc.binarySearchRecursive(arr, 5, 0, arr.length - 1));
    }

    @Test
    void testLowerBound() {
        double[] arr = {1, 2, 2, 2, 3};
        assertEquals(1, calc.lowerBound(arr, 2));
    }

    @Test
    void testUpperBound() {
        double[] arr = {1, 2, 2, 2, 3};
        assertEquals(4, calc.upperBound(arr, 2));
    }
}
