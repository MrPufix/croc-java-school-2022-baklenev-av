package ru.croc.task6;

public class Circle extends Figure {

    private final int radius;

    public Circle(int x, int y, int radius) {
        super("C", new Point(x, y));
        this.radius = radius;
    }

    @Override
    public String getPositionData() {
        String position = super.getPositionData();
        return position + ", " + radius;
    }
}
