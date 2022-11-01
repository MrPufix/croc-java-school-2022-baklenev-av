package ru.croc.task6;

public class Rectangle extends Figure {
    public Rectangle(int x1, int y1, int x2, int y2) {
        super("R", new Point(x1, y1), new Point(x2, y2));
    }
}
