public enum Cell {
    EMPTY,
    X,
    O;

    //converts str to Cells
    public static Cell getCellFromStr(char cellStr) {
        Cell cell = Cell.EMPTY;
        if (cellStr == 'X' || cellStr == 'x') {
            cell = Cell.X;
        }
        else if (cellStr == 'O' || cellStr == 'o') {
            cell = Cell.O;
        }
        else {
            System.err.println("Invalid Cell Type");
            System.exit(1);
        }
        return cell;
    }
}