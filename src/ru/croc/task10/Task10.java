package ru.croc.task10;

public class Task10 {
    public static void main(String[] args) {
        int threadsNumber = Integer.parseInt(args[0]);
        String passwordHash = args[1];

        System.out.println(Solution.calculatePassword(threadsNumber, passwordHash));
    }
}
