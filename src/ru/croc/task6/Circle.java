package ru.croc.task6;

public class Circle extends Figure {

    private final int radius;

    public Circle(int x, int y, int radius) {
        super("C", new Point(x, y));
        this.radius = radius;
    }

    @Override
    public boolean hasPoint(int x, int y) {
        Point center = getPoint(0);

        double dist = Math.sqrt(Math.pow(center.x - x, 2) + Math.pow(center.y - y, 2));

        return dist <= radius;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + radius;
    }
}
