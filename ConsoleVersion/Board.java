public class Board {
    Cell[][] cellState = new Cell[3][3];
    Cell whoWon = Cell.EMPTY;
    
    public Board() {
        //makes every cell empty
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                cellState[i][j] = Cell.EMPTY;
            }
        }
    }
    
    //Changes each Cell
    public void changeSymboll(int Row, int Collumn, Cell PlayerSymbool) {
        if(cellState[Row][Collumn] == Cell.EMPTY) {
            cellState[Row][Collumn] = PlayerSymbool;
        }
        else {
            System.err.println("Please Don't Insert at NonEmpty Cell");
            System.exit(1);
        }
    }

    //Winning Check
    public boolean didSomeoneWon() {
        //X row check
        if((cellState[0][0] == Cell.X && cellState[0][1] == Cell.X && cellState[0][2] == Cell.X)
            || (cellState[1][0] == Cell.X && cellState[1][1] == Cell.X && cellState[1][2] == Cell.X)
            || (cellState[2][0] == Cell.X && cellState[2][1] == Cell.X && cellState[2][2] == Cell.X)) {
                whoWon = Cell.X;
                return true;
        }
        //X collumn check
        else if((cellState[0][0] == Cell.X && cellState[1][0] == Cell.X && cellState[2][0] == Cell.X)
        || (cellState[0][1] == Cell.X && cellState[1][1] == Cell.X && cellState[2][1] == Cell.X)
        || (cellState[0][2] == Cell.X && cellState[1][2] == Cell.X && cellState[2][2] == Cell.X)) {
            whoWon = Cell.X;
            return true;
        }
        //X daigonal check
        else if((cellState[0][0] == Cell.X && cellState[1][1] == Cell.X && cellState[2][2] == Cell.X)
        || (cellState[0][2] == Cell.X && cellState[1][1] == Cell.X && cellState[2][0] == Cell.X)) {
            whoWon = Cell.X;
            return true;
        }
        //O row check
        if((cellState[0][0] == Cell.O && cellState[0][1] == Cell.O && cellState[0][2] == Cell.O)
            || (cellState[1][0] == Cell.O && cellState[1][1] == Cell.O && cellState[1][2] == Cell.O)
            || (cellState[2][0] == Cell.O && cellState[2][1] == Cell.O && cellState[2][2] == Cell.O)) {
                whoWon = Cell.O;
                return true;
        }
        //O collumn check
        else if((cellState[0][0] == Cell.O && cellState[1][0] == Cell.O && cellState[2][0] == Cell.O)
        || (cellState[0][1] == Cell.O && cellState[1][1] == Cell.O && cellState[2][1] == Cell.O)
        || (cellState[0][2] == Cell.O && cellState[1][2] == Cell.X && cellState[2][2] == Cell.O)) {
            whoWon = Cell.O;
            return true;
        }
        //O daigonal check
        else if((cellState[0][0] == Cell.O && cellState[1][1] == Cell.O && cellState[2][2] == Cell.O)
        || (cellState[0][2] == Cell.O && cellState[1][1] == Cell.O && cellState[2][0] == Cell.O)) {
            whoWon = Cell.O;
            return true;
        }
        else {
            return false;
        }
    }

    //check for occupied board
    public boolean isBoardFull() {
        int emptyCount = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(cellState[i][j] == Cell.EMPTY) {
                    emptyCount++;
                }
                else {
                    continue;
                }
            }
        }

        if (emptyCount != 0) {
            return false;
        }
        else {
            return true;
        }
    }
}
