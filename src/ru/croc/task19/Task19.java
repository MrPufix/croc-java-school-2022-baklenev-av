package ru.croc.task19;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Task19 {
    public static void main(String[] args) throws FileNotFoundException {
        String file = "task19.txt";
        try (PrintWriter pr = new PrintWriter(file)){
            pr.print("Hello, world!");
        }
    }
}
