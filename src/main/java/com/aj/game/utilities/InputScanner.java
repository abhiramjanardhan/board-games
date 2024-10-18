package com.aj.game.utilities;

import java.util.Scanner;

public class InputScanner {
    private final Scanner scanner;

    public InputScanner() {
        this.scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void closeScanner() {
        this.getScanner().close();
    }

    public String getString() {
        return this.getScanner().nextLine();
    }

    public int getInteger() {
        int value = this.getScanner().nextInt();
        this.scanner.nextLine();
        return value;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void print(String message) {
        System.out.print(message);
    }

    public void printNewLine() {
        System.out.println();
    }

    public void exit() {
        System.exit(0);
    }
}