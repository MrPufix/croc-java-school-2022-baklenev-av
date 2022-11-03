package ru.croc.task6;

public class Figure implements Movable {

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

    public boolean hasPoint(int x, int y) {
        for(Point point : points) {
            if(point.x == x && point.y == y)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getType() + " " + getPositionData();
    }

    @Override
    public void move(int dx, int dy) {
        for(Point point : points) {
            point.x += dx;
            point.y += dy;
        }
    }
}
