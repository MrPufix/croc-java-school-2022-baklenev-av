package ru.croc.task7;

public class IllegalMoveException extends Exception{

    private final ChessPosition startPos;
    private final ChessPosition endPos;

    IllegalMoveException(ChessPosition startPos, ChessPosition endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
    }

    @Override
    public String getMessage() {
        return "Can't move from " + startPos + " to " + endPos;
    }

    public ChessPosition getStartPos() {
        return startPos;
    }
    public ChessPosition getEndPos() {
        return endPos;
    }
}
