package ru.croc.task13;

import java.util.Scanner;
import java.io.FileNotFoundException;

public class Task13 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        FilmRecommendation filmRecommendation = new FilmRecommendation();

        String userHistory = scanner.nextLine();

        String recommendedFilm = filmRecommendation.getRecommendation(userHistory);

        System.out.println(recommendedFilm);
    }
}
