package ru.croc.task7;

import java.util.Scanner;

public class Task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");

        try {
            ChessPosition.checkHorseMovement(input);
            System.out.print("OK");
        } catch (IllegalMoveException e) {
            System.out.printf("Конь так не ходит: %s -> %s", e.getStartPos(), e.getEndPos());
        } catch (IllegalPositionException e) {
            System.out.println("Wrong position: " + e.getPosX() + ", " + e.getPosY());
            System.out.println(e.getMessage());
        }
    }
}
