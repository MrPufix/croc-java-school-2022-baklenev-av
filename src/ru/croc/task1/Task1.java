package ru.croc.task1;

import java.util.Scanner;

public class Task1 {

    static class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double findTriangleSquare(Point p0, Point p1, Point p2) {
        //Расстояние между точками 0 и 1
        double a = Math.sqrt(Math.pow(p0.x - p1.x, 2) + Math.pow(p0.y - p1.y, 2));
        //Расстояние между точками 1 и 2
        double b = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
        //Расстояние между точками 2 и 0
        double c = Math.sqrt(Math.pow(p2.x - p0.x, 2) + Math.pow(p2.y - p0.y, 2));

        double p = (a + b + c) / 2;

        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Point[] points = new Point[3];

        for (int i = 0; i < 3; i++) {
            double x, y;
            System.out.printf("Введите координату x вершины №%d: ", i + 1);
            x = scanner.nextDouble();
            System.out.printf("Введите координату y вершины №%d: ", i + 1);
            y = scanner.nextDouble();
            points[i] = new Point(x , y);
        }

        System.out.printf("Площадь треугольника: %.1f",
                findTriangleSquare(points[0], points[1], points[2]));
    }
}