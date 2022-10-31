package ru.croc.task2;

import java.util.Scanner;

public class Task2 {
    public static void printBytes(double bytes) {
        String[] names = new String[] {"B", "KB", "MB", "GB", "TB", "PB"};

        int count = 0;
        while (bytes >= 1024 && count < (names.length - 1)) {
            bytes /= 1024;
            count++;
        }

        System.out.printf("%.1f %s", bytes, names[count]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите байты: ");
        double bytes = scanner.nextDouble();

        printBytes(bytes);
    }
}
