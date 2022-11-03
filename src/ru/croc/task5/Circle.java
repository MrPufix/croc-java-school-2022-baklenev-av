package ru.croc.task5;

public class Circle extends Figure {

    private final int radius;

    public Circle(int x, int y, int radius) {
        super("C", new Point(x, y));
        this.radius = radius;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + radius;
    }
}
