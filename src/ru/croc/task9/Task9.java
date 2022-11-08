package ru.croc.task9;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Task9 {
    public static String normalizePath(String pathString) {
        Path path = Paths.get(pathString);
        return path.normalize().toString().replace('\\', '/');
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String path = scanner.nextLine();

        System.out.print(normalizePath(path));
    }
}
