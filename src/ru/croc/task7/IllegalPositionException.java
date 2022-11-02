package ru.croc.task7;

public class IllegalPositionException extends Exception {

    private final int posX, posY;
    private final int minX, minY;
    private final int maxX, maxY;

    IllegalPositionException(int posX, int posY, int minX, int minY, int maxX, int maxY) {
        this.posX = posX;
        this.posY = posY;

        this.minX = minX;
        this.minY = minY;

        this.maxX = maxX;
        this.maxY = maxY;
    }

    @Override
    public String getMessage() {
        return "Position should be from (%d, %d) to (%d, %d)".formatted(minX, minY, maxX, maxY);
    }

    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public int getMinX() {
        return minX;
    }
    public int getMinY() {
        return minY;
    }
    public int getMaxX() {
        return maxX;
    }
    public int getMaxY() {
        return maxY;
    }
}
