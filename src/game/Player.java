package game;

public class Player {
    String name;
    Cell symboll;

    //player Constructor
    public Player(String Name, Cell Symboll) {
        name = Name;
        symboll = Symboll;
    }
    
    public String getName() { return name; }
    public Cell getSymboll() { return symboll; }
}
