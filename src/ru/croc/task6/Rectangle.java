package ru.croc.task6;

public class Rectangle extends Figure {
    public Rectangle(int x1, int y1, int x2, int y2) {
        super("R", new Point(x1, y1), new Point(x2, y2));
    }

    @Override
    public boolean hasPoint(int x, int y) {
        Point a = getPoint(0);
        Point b = getPoint(1);

        int max_x = Integer.max(a.x, b.x);
        int min_x = Integer.min(a.x, b.x);

        int max_y = Integer.max(a.y, b.y);
        int min_y = Integer.min(a.y, b.y);

        return (x >= min_x && x <= max_x) && (y >= min_y && y <= max_y);
    }
}
