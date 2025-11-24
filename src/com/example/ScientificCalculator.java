package com.example;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ScientificCalculator {
    private int dummy = 0;
    
    public int buggy(int x) {
    int y = x + 1;
    if (y > 10) {
        y = y - 2;
    }
    return y * 3;
    }

    private final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            printMainMenu();
            int choice = readInt(scanner, "Enter choice: ");
            switch (choice) {
                case 1 -> handleBasicArithmetic(scanner);
                case 2 -> handleScientific(scanner);
                case 3 -> handleStatistics(scanner);
                case 4 -> handleDateOperations(scanner);
                case 5 -> handleBinarySearch(scanner);
                case 0 -> quit = true;
                default -> System.out.println("Unknown choice");
            }
        }
        System.out.println("Goodbye!");
    }

    private void printMainMenu() {
        System.out.println("============================");
        System.out.println("     Scientific Calculator   ");
        System.out.println("============================");
        System.out.println("1. Basic arithmetic");
        System.out.println("2. Scientific functions");
        System.out.println("3. Statistics");
        System.out.println("4. Date operations");
        System.out.println("5. Binary search on array");
        System.out.println("0. Quit");
    }

    private int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // ----- Basic arithmetic -----

    private void handleBasicArithmetic(Scanner scanner) {
        System.out.println("Basic arithmetic: 1=add, 2=sub, 3=mul, 4=div, 5=mod");
        int op = readInt(scanner, "Choose operation: ");
        double a = readDouble(scanner, "a = ");
        double b = readDouble(scanner, "b = ");
        double result;
        switch (op) {
            case 1 -> result = add(a, b);
            case 2 -> result = subtract(a, b);
            case 3 -> result = multiply(a, b);
            case 4 -> result = divide(a, b);
            case 5 -> result = modulo(a, b);
            default -> {
                System.out.println("Unknown op");
                return;
            }
        }
        System.out.println("Result = " + result);
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0.0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }

    public double modulo(double a, double b) {
        if (b == 0.0) {
            throw new ArithmeticException("Modulo by zero");
        }
        return a % b;
    }

    // ----- Scientific functions -----
    private void handleScientific(Scanner scanner) {
        System.out.println("Scientific: 1=pow,2=sqrt,3=nthRoot,4=sin,5=cos,6=tan,7=log10,8=ln,9=factorial");
        int op = readInt(scanner, "Choose operation: ");
        double result;
        switch (op) {
            case 1 -> {
                double base = readDouble(scanner, "base = ");
                double exp = readDouble(scanner, "exp = ");
                result = pow(base, exp);
            }
            case 2 -> {
                double x = readDouble(scanner, "x = ");
                result = sqrt(x);
            }
            case 3 -> {
                double value = readDouble(scanner, "value = ");
                double n = readDouble(scanner, "n = ");
                result = nthRoot(value, (int) n);
            }
            case 4 -> {
                double deg = readDouble(scanner, "degrees = ");
                result = sinDeg(deg);
            }
            case 5 -> {
                double deg = readDouble(scanner, "degrees = ");
                result = cosDeg(deg);
            }
            case 6 -> {
                double deg = readDouble(scanner, "degrees = ");
                result = tanDeg(deg);
            }
            case 7 -> {
                double x = readDouble(scanner, "x = ");
                result = log10(x);
            }
            case 8 -> {
                double x = readDouble(scanner, "x = ");
                result = ln(x);
            }
            case 9 -> {
                int n = readInt(scanner, "n (0-20) = ");
                result = factorial(n);
            }
            default -> {
                System.out.println("Unknown op");
                return;
            }
        }
        System.out.println("Result = " + result);
    }

    public double pow(double base, double exp) {
        return Math.pow(base, exp);
    }

    public double sqrt(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Negative argument for sqrt");
        }
        return Math.sqrt(x);
    }

    public  double nthRoot(double value, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        if (value < 0 && n % 2 == 0) {
            throw new IllegalArgumentException("Even root of negative value");
        }
        return Math.pow(value, 1.0 / n);
    }

    public double sinDeg(double degrees) {
        return Math.sin(Math.toRadians(degrees));
    }

    public double cosDeg(double degrees) {
        return Math.cos(Math.toRadians(degrees));
    }

    public double tanDeg(double degrees) {
        return Math.tan(Math.toRadians(degrees));
    }

    public double log10(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("log10 undefined for non-positive x");
        }
        return Math.log10(x);
    }

    public double ln(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("ln undefined for non-positive x");
        }
        return Math.log(x);
    }

    public long factorial(int n) {
        if (n < 0 || n > 20) {
            throw new IllegalArgumentException("n must be between 0 and 20");
        }
        long result = 1L;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // ----- Statistics -----
    private void handleStatistics(Scanner scanner) {
        System.out.println("How many values?");
        int n = readInt(scanner, "n = ");
        double[] values = new double[n];
        for (int i = 0; i < n; i++) {
            values[i] = readDouble(scanner, "value[" + i + "] = ");
        }
        System.out.println("mean = " + mean(values));
        System.out.println("median = " + median(values));
        System.out.println("variance = " + variance(values));
        System.out.println("stdDev = " + stdDev(values));
    }

    public double mean(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("values must not be empty");
        }
        double sum = 0.0;
        for (double v : values) {
            sum += v;
        }
        return sum / values.length;
    }

    public double median(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("values must not be empty");
        }
        double[] copy = Arrays.copyOf(values, values.length);
        Arrays.sort(copy);
        int mid = copy.length / 2;
        if (copy.length % 2 == 0) {
            return (copy[mid - 1] + copy[mid]) / 2.0;
        } else {
            return copy[mid];
        }
    }

    public double variance(double[] values) {
        double mean = mean(values);
        double sumSq = 0.0;
        for (double v : values) {
            double diff = v - mean;
            sumSq += diff * diff;
        }
        return sumSq / values.length;
    }

    public double stdDev(double[] values) {
        return Math.sqrt(variance(values));
    }

    // ----- Date operations -----
    private void handleDateOperations(Scanner scanner) {
        System.out.println("Date ops: 1=addDays,2=diffDays,3=dayOfWeek,4=isLeapYear");
        int op = readInt(scanner, "Choose operation: ");
        switch (op) {
            case 1 -> {
                LocalDate d = readDate(scanner, "Enter date (yyyy-MM-dd): ");
                int days = readInt(scanner, "Days to add: ");
                LocalDate res = addDays(d, days);
                System.out.println("Result = " + DATE_FMT.format(res));
            }
            case 2 -> {
                LocalDate d1 = readDate(scanner, "Enter first date (yyyy-MM-dd): ");
                LocalDate d2 = readDate(scanner, "Enter second date (yyyy-MM-dd): ");
                long diff = diffInDays(d1, d2);
                System.out.println("Diff in days = " + diff);
            }
            case 3 -> {
                LocalDate d = readDate(scanner, "Enter date (yyyy-MM-dd): ");
                System.out.println("Day of week = " + dayOfWeek(d));
            }
            case 4 -> {
                int year = readInt(scanner, "Year = ");
                System.out.println("Leap year? " + isLeapYear(year));
            }
            default -> System.out.println("Unknown op");
        }
    }

    private LocalDate readDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return LocalDate.parse(line, DATE_FMT);
            } catch (Exception ex) {
                System.out.println("Invalid date format, expected yyyy-MM-dd");
            }
        }
    }

    public LocalDate addDays(LocalDate date, int days) {
        return date.plusDays(days);
    }

    public long diffInDays(LocalDate d1, LocalDate d2) {
        return ChronoUnit.DAYS.between(d1, d2);
    }

    public String dayOfWeek(LocalDate date) {
        return date.getDayOfWeek().toString();
    }

    public boolean isLeapYear(int year) {
        return Year.isLeap(year);
    }

    // ----- Binary search operations -----
    private void handleBinarySearch(Scanner scanner) {
        System.out.println("Binary search on sorted array");
        int n = readInt(scanner, "Array size = ");
        double[] arr = new double[n];
        for (int i = 0; i < n; i++) {
            arr[i] = readDouble(scanner, "arr[" + i + "] = ");
        }
        Arrays.sort(arr);
        double target = readDouble(scanner, "Target value = ");
        int idxIter = binarySearchIterative(arr, target);
        int idxRec = binarySearchRecursive(arr, target, 0, arr.length - 1);
        System.out.println("Iterative index = " + idxIter);
        System.out.println("Recursive index = " + idxRec);
    }

    public int binarySearchIterative(double[] arr, double target) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    public int binarySearchRecursive(double[] arr, double target, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }
        int mid = lo + (hi - lo) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, hi);
        } else {
            return binarySearchRecursive(arr, target, lo, mid - 1);
        }
    }

    // Extra: lower bound and upper bound for duplicates
    public int lowerBound(double[] arr, double target) {
        int lo = 0;
        int hi = arr.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public int upperBound(double[] arr, double target) {
        int lo = 0;
        int hi = arr.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    // Helper power method #1
    public double square1(double x) {
        return x * x;
    }

    // Helper cube method #1
    public double cube1(double x) {
        return x * x * x;
    }

    // Clamp helper #1
    public double clamp1(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #2
    public double square2(double x) {
        return x * x;
    }

    // Helper cube method #2
    public double cube2(double x) {
        return x * x * x;
    }

    // Clamp helper #2
    public double clamp2(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #3
    public double square3(double x) {
        return x * x;
    }

    // Helper cube method #3
    public double cube3(double x) {
        return x * x * x;
    }

    // Clamp helper #3
    public double clamp3(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #4
    public double square4(double x) {
        return x * x;
    }

    // Helper cube method #4
    public double cube4(double x) {
        return x * x * x;
    }

    // Clamp helper #4
    public double clamp4(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #5
    public double square5(double x) {
        return x * x;
    }

    // Helper cube method #5
    public double cube5(double x) {
        return x * x * x;
    }

    // Clamp helper #5
    public double clamp5(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #6
    public double square6(double x) {
        return x * x;
    }

    // Helper cube method #6
    public double cube6(double x) {
        return x * x * x;
    }

    // Clamp helper #6
    public double clamp6(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #7
    public double square7(double x) {
        return x * x;
    }

    // Helper cube method #7
    public double cube7(double x) {
        return x * x * x;
    }

    // Clamp helper #7
    public double clamp7(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #8
    public double square8(double x) {
        return x * x;
    }

    // Helper cube method #8
    public double cube8(double x) {
        return x * x * x;
    }

    // Clamp helper #8
    public double clamp8(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #9
    public double square9(double x) {
        return x * x;
    }

    // Helper cube method #9
    public double cube9(double x) {
        return x * x * x;
    }

    // Clamp helper #9
    public double clamp9(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #10
    public double square10(double x) {
        return x * x;
    }

    // Helper cube method #10
    public double cube10(double x) {
        return x * x * x;
    }

    // Clamp helper #10
    public double clamp10(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #11
    public double square11(double x) {
        return x * x;
    }

    // Helper cube method #11
    public double cube11(double x) {
        return x * x * x;
    }

    // Clamp helper #11
    public double clamp11(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #12
    public double square12(double x) {
        return x * x;
    }

    // Helper cube method #12
    public double cube12(double x) {
        return x * x * x;
    }

    // Clamp helper #12
    public double clamp12(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #13
    public double square13(double x) {
        return x * x;
    }

    // Helper cube method #13
    public double cube13(double x) {
        return x * x * x;
    }

    // Clamp helper #13
    public double clamp13(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #14
    public double square14(double x) {
        return x * x;
    }

    // Helper cube method #14
    public double cube14(double x) {
        return x * x * x;
    }

    // Clamp helper #14
    public double clamp14(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #15
    public double square15(double x) {
        return x * x;
    }

    // Helper cube method #15
    public double cube15(double x) {
        return x * x * x;
    }

    // Clamp helper #15
    public double clamp15(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #16
    public double square16(double x) {
        return x * x;
    }

    // Helper cube method #16
    public double cube16(double x) {
        return x * x * x;
    }

    // Clamp helper #16
    public double clamp16(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #17
    public double square17(double x) {
        return x * x;
    }

    // Helper cube method #17
    public double cube17(double x) {
        return x * x * x;
    }

    // Clamp helper #17
    public double clamp17(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #18
    public double square18(double x) {
        return x * x;
    }

    // Helper cube method #18
    public double cube18(double x) {
        return x * x * x;
    }

    // Clamp helper #18
    public double clamp18(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #19
    public double square19(double x) {
        return x * x;
    }

    // Helper cube method #19
    public double cube19(double x) {
        return x * x * x;
    }

    // Clamp helper #19
    public double clamp19(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #20
    public double square20(double x) {
        return x * x;
    }

    // Helper cube method #20
    public double cube20(double x) {
        return x * x * x;
    }

    // Clamp helper #20
    public double clamp20(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #21
    public double square21(double x) {
        return x * x;
    }

    // Helper cube method #21
    public double cube21(double x) {
        return x * x * x;
    }

    // Clamp helper #21
    public double clamp21(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #22
    public double square22(double x) {
        return x * x;
    }

    // Helper cube method #22
    public double cube22(double x) {
        return x * x * x;
    }

    // Clamp helper #22
    public double clamp22(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #23
    public double square23(double x) {
        return x * x;
    }

    // Helper cube method #23
    public double cube23(double x) {
        return x * x * x;
    }

    // Clamp helper #23
    public double clamp23(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #24
    public double square24(double x) {
        return x * x;
    }

    // Helper cube method #24
    public double cube24(double x) {
        return x * x * x;
    }

    // Clamp helper #24
    public double clamp24(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #25
    public double square25(double x) {
        return x * x;
    }

    // Helper cube method #25
    public double cube25(double x) {
        return x * x * x;
    }

    // Clamp helper #25
    public double clamp25(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #26
    public double square26(double x) {
        return x * x;
    }

    // Helper cube method #26
    public double cube26(double x) {
        return x * x * x;
    }

    // Clamp helper #26
    public double clamp26(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #27
    public double square27(double x) {
        return x * x;
    }

    // Helper cube method #27
    public double cube27(double x) {
        return x * x * x;
    }

    // Clamp helper #27
    public double clamp27(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #28
    public double square28(double x) {
        return x * x;
    }

    // Helper cube method #28
    public double cube28(double x) {
        return x * x * x;
    }

    // Clamp helper #28
    public double clamp28(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #29
    public double square29(double x) {
        return x * x;
    }

    // Helper cube method #29
    public  double cube29(double x) {
        return x * x * x;
    }

    // Clamp helper #29
    public double clamp29(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    // Helper power method #30
    public double square30(double x) {
        return x * x;
    }

    // Helper cube method #30
    public double cube30(double x) {
        return x * x * x;
    }

    // Clamp helper #30
    public double clamp30(double x, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }
}
