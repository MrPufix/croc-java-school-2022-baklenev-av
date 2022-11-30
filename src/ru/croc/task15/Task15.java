package ru.croc.task15;

import java.io.FileNotFoundException;

public class Task15 {
    public static void main(String[] args) throws FileNotFoundException {
        DepartmentConfiguration configuration = DepartmentConfiguration.createFromFile("Task15InputFile.txt");
        System.out.println(configuration.countRequestHandleTime());
    }
}
