package ru.croc.task9;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Task9 {
    public static String normalizePath(String pathString) {
        Path path = Paths.get(pathString);

        path = path.normalize();

        String normalizedPath = path.toString();
        normalizedPath = normalizedPath.replace('\\', '/');

        return normalizedPath;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String path = scanner.nextLine();
            System.out.print(normalizePath(path));
        }
    }
}
