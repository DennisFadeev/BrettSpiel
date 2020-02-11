package edu.kit.informatik;

import java.util.Stack;
/**
 * 
 * @author Dennis Fadeev
 * @version 1.0
 * 
 * Diese Klasse beinhaltet alle Befehle, die beim Spiel
 * ausgef�hrt werden k�nnen.
 *
 */
public class Game {
    /**
     * Mithilfe dieses Stack wird die Anzahl der Z�ge gez�hlt,
     * und das oberste Element des Stapels beinhaltet einen String,
     * welcher den Spieler darstellt der gewonnen hat, also P1 oder P2.
     */
    private Stack<String> turns = new Stack<>();
    /**
     * Hier wird ein zweidimensionales Arra erzeugt, dass das Spielfeld darstellt.
     */
    private Stone[][] field = new Stone[6][6];
    /**
     * Dieses Array stellt den "bag" dar, der die Spielsteine beinhaltet.
     */
    private Stone[] stones = new Stone[] {new Stone(Color.BLACK, Shape.SQUARE, Size.SMALL, Density.HOLLOW, 0),
            new Stone(Color.BLACK, Shape.SQUARE, Size.SMALL, Density.MASSIVE, 1),
            new Stone(Color.BLACK, Shape.SQUARE, Size.BIG, Density.HOLLOW, 2),
            new Stone(Color.BLACK, Shape.SQUARE, Size.BIG, Density.HOLLOW, 3),
            new Stone(Color.BLACK, Shape.CYLINDRICALLY, Size.SMALL, Density.HOLLOW, 4),
            new Stone(Color.BLACK, Shape.CYLINDRICALLY, Size.SMALL, Density.MASSIVE, 5),
            new Stone(Color.BLACK, Shape.CYLINDRICALLY, Size.BIG, Density.HOLLOW, 6),
            new Stone(Color.BLACK, Shape.CYLINDRICALLY, Size.BIG, Density.MASSIVE, 7),
            new Stone(Color.WHITE, Shape.SQUARE, Size.SMALL, Density.HOLLOW, 8),
            new Stone(Color.WHITE, Shape.SQUARE, Size.SMALL, Density.MASSIVE, 9),
            new Stone(Color.WHITE, Shape.SQUARE, Size.BIG, Density.HOLLOW, 10),
            new Stone(Color.WHITE, Shape.SQUARE, Size.BIG, Density.MASSIVE, 11),
            new Stone(Color.WHITE, Shape.CYLINDRICALLY, Size.SMALL, Density.HOLLOW, 12),
            new Stone(Color.WHITE, Shape.CYLINDRICALLY, Size.SMALL, Density.MASSIVE, 13),
            new Stone(Color.WHITE, Shape.CYLINDRICALLY, Size.BIG, Density.HOLLOW, 14),
            new Stone(Color.WHITE, Shape.CYLINDRICALLY, Size.BIG, Density.MASSIVE, 15) };
    /**
     * Dieser boolean wird daf�r verwendet, falls "place" fehlschl�gt, um den Stack
     * der die Z�ge beinhaltet ordnungsgem�� im Falle des fehlgeschlagenen Befehls zu bearbeiten.
     */
    private boolean delete;    
    /**
     * Platzhalter der den in "select" ausgew�hlten Spielstein speichert.
     */
    private Stone current;
    /**
     * Konstruktor f�r die Game-Klasse.
     */
    public Game() { }
    /**
     * Diese Methode w�hlt einen Spielstein aus.
     * @param n
     * Dieser Int-Wert stellt den Spielstein dar,
     * der ausgew�hlt werden soll.
     * 
     * @throws InvalidStoneException
     * Wirft eine Exception falls, ein bestimmter Stein bereits schon einmal
     * ausgew�hlt wurde, oder falls momentan schon ein Stein ausgew�hlt ist.
     */
    void select(int n) throws InvalidStoneException {
        try {
            if (current == null) {
                if (stones[n].getFree() != false) {
                    current = stones[n];
                    delete = true;                    
                } else {
                    throw new InvalidStoneException("This stone has already been picked!");
                }
            } else {
                throw new InvalidStoneException("A stone has already been selected, place it!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("This stone doesn't exist!");
        }
    }
   
    /**
     * Diese Methode beinhaltet den "place"-Befehl f�r den
     * Standard-Modus.
     * 
     * @param i
     * Diese Variable stellt die Zeile dar.
     * 
     * @param j
     * Diese Variable stellt die Spalte dar.
     * 
     * @return current
     * Gibt den Platzhalter f�r den Spielstein zur�ck.
     * 
     * @throws FieldException
     * Diese Exception wird geworfen wenn kein Stein ausgew�hlt
     * wurde, oder das ausgew�hlte Feld nicht frei ist.
     * 
     * @throws NumberFormatException
     * Diese Exception wird geworfen, wenn keine
     * nat�rliche Zahl als Parameter eingegeben wird.
     */
    Stone place(int i, int j) throws FieldException, NumberFormatException {
        try {
            if (current != null) {
                if (field[i][j] == null) {
                    field[i][j] = current;
                    current.setFree(false);
                    current = null;
                    if (turns.size() == 0) {
                        turns.push("P2");
                        return current;
                    }
                    if (turns.peek().equals("P1")) {
                        turns.push("P2");
                        return current;
                    }

                    if (turns.peek().equals("P2")) {
                        turns.push("P1");
                        return current;
                    }
                } else {
                    throw new FieldException("This field is not empty, pick another one!");
                }
            } else {
                throw new FieldException("No stone selected!");
            }

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid Number format!");
        }
        return current;
    }

    /**
     * Diese Methode beinhaltet die place-Methode f�r den Torus-Modus.
     * 
     * @param i
     * Diese Variable stellt die Zeile dar.
     * 
     * @param j
     * Diese Variable stellt die Spalte dar.
     * 
     * @return current
     * Gibt den Platzhalter f�r den Spielstein zur�ck.
     * 
     * @throws FieldException
     * Wird geworfen wenn kein Stein ausgew�hlt ist, oder
     * das ausgew�hlte Feld nicht leer ist.
     * 
     * @throws NumberFormatException
     * Wird geworfen wenn keine Ganzzahl eingegeben wird
     * als Parameter.
     */
    public Stone placeTorus(int i, int j) throws FieldException, NumberFormatException {
        try {
            int row = i;
            int column = j; 
            if (current != null) {
                if (row > 5) {
                    row = row % 6;
                }
                if (column > 5) {
                    column = column % 6;
                }
                if (row < 0) {
                    row = ((row % 6) + 6) % 6;
                }
                if (column < 0) {
                    column = ((column % 6) + 6) % 6;
                }
                if (field[row][column] == null) {
                    field[row][column] = current;
                    current.setFree(false);
                    current = null;
                    if (turns.size() == 0) {
                        turns.push("P2");
                        return current;
                    }
                    if (turns.peek().equals("P1")) {
                        turns.push("P2");
                        return current;
                    }
                    if (turns.peek().equals("P2")) {
                        turns.push("P1");
                        return current;
                    }
                } else {
                    throw new FieldException("This field is not empty, pick another one!");
                }
            } else {
                throw new FieldException("No stone selected!");
            }

        } catch (NumberFormatException e) {
            current = null;
            if (turns.size() != 0 && delete == true) {
                turns.pop();
                delete = false;
            }
            throw new NumberFormatException("Invalid Number Format!");
        }
        return current;
    }
    /**
     * Diese Methode ist dazu da einen String zu erzeugen der alle verf�gbaren
     * Spielsteine, mittels eines Stringbuilder, ausgibt.
     * 
     * @return s
     * Gibt den String zur�ck, der alle verf�gbaren Spielsteine beinhaltet.
     */
    String bag() {
        String s = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stones.length; i++) {
            Stone stone = stones[i];
            if (stone.getFree() != false) {
                sb.append(i + " ");
            }
            s = sb.toString();
        }
        return s;
    }
    /**
     * Diese Methode erzeugt einen String, der eine bestimmte Zeile beinhaltet.
     * Falls ein Feld in dieser Zeile leer ist, wird "#" angezeigt, falls nicht
     * die entsprechende Nummer des Steines.
     * 
     * @param n Stellt die Zeile dar, die ausgegeben werden soll.
     * 
     * @return s
     * Gibt den String zur�ck, der die Zeile beinhaltet.
     */
    String rowprint(int n) {
        String s = "";
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 6; j++) {
            if (field[n][j] != null) {
                sb.append(field[n][j].getId() + " ");
            } else {
                sb.append('#' + " ");
            }
            s = sb.toString();
        }
        return s;
    }
    /**
     * Diese Methode erzeugt einen String, der eine bestimmte Spalte beinhaltet.
     * Falls ein Feld in dieser Spalte leer ist, wird "#" angezeigt, falls nicht
     * die entsprechende Nummer des Steines.
     * 
     * @param n Stellt die Spalte dar, die ausgegeben werden soll.
     * 
     * @return s
     * Gibt den String zur�ck, der die Spalte beinhaltet.
     */
    String colprint(int n) {
        String s = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (field[i][n] != null) {
                sb.append(field[i][n].getId() + " ");
            } else {
                sb.append("# ");
            }
            s = sb.toString();
        }
        return s;
    }
    /**
     * Diese Methode setzt ein Spiel zur�ck, und wird immer dann ausgef�hrt
     * wenn eines neues Spiel gestartet wird, sprich bspw. alle Spielsteine sind wieder verf�gbar,
     * das Feld ist wieder leer, die Z�ge werden zur�ckgesetzt etc.
     */
    void reset() {
        for (int k = 0; k < stones.length; k++) {
            Stone stone = stones[k];
            stone.setFree(true);
        }
        int i = 0;
        int j = 0;
        for (int z = 0; z < 36; z++) {
            field[i][j] = null;
            j++;
            if (j == 6) {
                j = 0;
                i++;
            }
        }
        CheckAlgorithm.setWin(false);
        turns.clear();
        CheckAlgorithm.getWinningStones().clear();
    }
    /**
     * Setter f�r den Platzhalter der Spielsteine.
     * @param st
     * Stellt den Wert dar, den "current" haben soll.
     */
    public void setCurrent(Stone st) {
        this.current = st;
    }
    /**
     * Getter f�r die Variable die verwendet wird,
     * falls "place" fehlschl�gt.
     * @return delete
     * Gibt delete zur�ck.
     */
    public boolean getDelete() {
        return delete;
    }
    /**
     * Setter f�r die Variable die verwendet wird,
     * falls "place" fehlschl�gt.
     * @param bl
     * Stellt den Wert dar, den "delete" haben soll.
     */
    public void setDelete(boolean bl) {
        this.delete = bl;
    }
    /**
     * Getter f�r den Stapel.
     * @return turns
     * Gibt den Stack zur�ck.
     */
    public Stack<String> getTurns() {
        return turns;
    }
    /**
     * Getter f�r das Spielfeld.
     * @return field
     * Gibt das Spielfeld zur�ck.
     */
    public Stone[][] getField() {
        return field;
    }
}
