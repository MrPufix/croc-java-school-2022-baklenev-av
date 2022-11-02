package ru.croc.task7;

public class ChessPosition {
    private final int x;
    private final int y;

    ChessPosition(int x, int y) throws IllegalPositionException {
        if((x < 0 || x > 7) || (y < 0 || y > 7)) {
            throw new IllegalPositionException(x, y, 0, 0, 7, 7);
        }

        this.x = x;
        this.y = y;
    }

    static ChessPosition parse (String position) throws IllegalPositionException {
        char posLetter = position.charAt(0);
        char posNumber = position.charAt(1);

        int x = posLetter - 97;
        int y = posNumber - 49;

        return new ChessPosition(x, y);
    }

    static void checkHorseMovement(String[] movement) throws IllegalMoveException, IllegalPositionException {
        ChessPosition currentPos = ChessPosition.parse(movement[0]);
        ChessPosition nextPos;

        int[] possibleMovesX = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] possibleMovesY = {-2, -1, 1, 2, 2, 1, -1, -2};

        for(int i = 1; i < movement.length; i++) {
            nextPos = ChessPosition.parse(movement[i]);

            int nextPosX = nextPos.x;
            int nextPosY = nextPos.y;

            int currentPosX = currentPos.x;
            int currentPosY = currentPos.y;

            boolean canMove = false;
            for(int j = 0; j < possibleMovesX.length; j++) {
                if(currentPosX + possibleMovesX[j] == nextPosX && currentPosY + possibleMovesY[j] == nextPosY) {
                    canMove = true;
                    break;
                }
            }

            if(!canMove)
                throw new IllegalMoveException(currentPos.toString(), nextPos.toString());

            currentPos = nextPos;
        }
    }

    @Override
    public String toString() {
        char posLetter = (char)(x + 97);
        return String.valueOf(posLetter) + (y+1);
    }
}
