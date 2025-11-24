Mutation Testing Guide: Scientific Calculator
This guide explains how to set up and run mutation testing (using PITest) for the Scientific Calculator project manually via the command line.

1. Prerequisites & Setup
Folder Structure
Ensure your project directory looks exactly like this:

project-root/
│
├── src/
│   └── com/example/ScientificCalculator.java
│
├── test/
│   └── com/example/ScientificCalculatorTest.java
│
├── lib/  (For JUnit)
│   └── junit-platform-console-standalone-1.13.0-M3.jar
│
├── pit/  (For PITest)
│   ├── pitest-1.22.0.jar
│   ├── pitest-command-line-1.22.0.jar
│   ├── pitest-entry-1.22.0.jar
│   └── pitest-junit5-plugin-1.2.3.jar  <-- CRITICAL for JUnit 5 support
│
└── out/  (Generated automatically)

Here is a comprehensive README.md file. You can save this as README.md or INSTRUCTIONS.txt in your project root.

It covers downloading the JARs, setting up the folders, compiling, and running the test.

Mutation Testing Guide: Scientific Calculator
This guide explains how to set up and run mutation testing (using PITest) for the Scientific Calculator project manually via the command line.

1. Prerequisites & Setup
Folder Structure
Ensure your project directory looks exactly like this:


# Mutation Testing Project: Scientific Calculator

This project implements **Mutation Testing** using **PITest** and **JUnit 5** for a Scientific Calculator application. The goal is to evaluate the quality of the test suite by ensuring it can detect artificial faults (mutants) introduced into the code.

## 📂 Project Structure

Ensure your directory looks exactly like this to match the commands below:

```text
project-root/
│
├── src/
│   └── com/example/ScientificCalculator.java
│
├── test/
│   └── com/example/ScientificCalculatorTest.java
│
├── lib/
│   └── junit-platform-console-standalone-1.13.0-M3.jar
│
├── pit/
│   ├── pitest-1.22.0.jar
│   ├── pitest-command-line-1.22.0.jar
│   ├── pitest-entry-1.22.0.jar
│   └── pitest-junit5-plugin-1.2.3.jar  <-- Essential for JUnit 5
│
└── out/  (Generated during compilation)

Downloads

If you are missing files, download them:

JUnit 5 Console: Maven Central - JUnit Platform Console (Place in lib/)

PITest Command Line: PITest Download (Place in pit/)

PITest JUnit 5 Plugin: Maven Central - PIT JUnit 5 Plugin (Place in pit/)

2. Compilation
Open PowerShell in the project-root and run these commands to compile the source code and the tests.

Step 1: Clean previous builds

Remove-Item -Recurse -Force out -ErrorAction SilentlyContinue

Step 2: Compile the Main Application

javac --release 17 -d out/main src/com/example/ScientificCalculator.java 

Step 3: Compile the Tests

javac -cp "lib/junit-platform-console-standalone-1.13.0-M3.jar;out/main" -d out/test test/com/example/ScientificCalculatorTest.java

Step 4: run normal tests:

java -jar lib/junit-platform-console-standalone-1.13.0-M3.jar -cp "out/main;out/test" --scan-classpath

3. Running Mutation Testing
We use a specific command that excludes the CLI interface (main, Scanner) and the helper bloat methods (square*, clamp*) to prevent infinite loops and ensure accurate scoring.

Run this single command:

java -cp "lib/*;pit/*;out/main;out/test" org.pitest.mutationtest.commandline.MutationCoverageReport --reportDir ./pit-reports --targetClasses com.example.ScientificCalculator --targetTests com.example.ScientificCalculatorTest --sourceDirs src --outputFormats HTML --excludedMethods "main,read*,print*,handle*,square*,cube*,clamp*"

4. Viewing Results
After the command finishes, a folder named pit-reports will be created.

Open the folder inside it (it will be timestamped).

Double-click index.html to open it in your browser.

How to read the report:
Green: The mutant was killed (Good! Your test caught the bug).

Red (Survived): The mutant survived (Bad. The code was changed, but your tests still passed. You need to write a better test case).

Red (No Coverage): The code was never run by any test.

Troubleshooting
Minion Exited / Timed Out: This usually means a mutation caused an infinite loop. PIT kills these automatically, so it counts as a pass.

0% Mutation Score: Ensure you included the pitest-junit5-plugin jar in the pit/ folder. Without it, PIT cannot find your tests.