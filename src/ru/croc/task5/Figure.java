package ru.croc.task5;

public class Figure {

    private final String type;
    private final Point[] points;

    public Figure(String type, Point... points) {
        this.type = type;
        this.points = points;
    }

    public String getType() {
        return type;
    }

    public String getPositionData() {
        StringBuilder position = new StringBuilder();
        for(int i = 0; i < points.length; i++) {
            position.append("(%d, %d)".formatted(points[i].x, points[i].y));
            if(i != points.length - 1)
                position.append(", ");
        }
        return position.toString();
    }

    @Override
    public String toString() {
        return getType() + " " + getPositionData();
    }
}
