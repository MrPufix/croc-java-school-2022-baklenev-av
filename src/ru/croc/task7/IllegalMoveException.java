package ru.croc.task7;

public class IllegalMoveException extends Exception{

    private final String startPos;
    private final String endPos;

    IllegalMoveException(String startPos, String endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
    }

    @Override
    public String getMessage() {
        return "Can't move from " + startPos + " to " + endPos;
    }

    public String getStartPos() {
        return startPos;
    }
    public String getEndPos() {
        return endPos;
    }
}
