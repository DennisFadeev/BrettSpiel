package edu.kit.informatik;

import java.util.Stack;
/**
 * 
 * @author Dennis Fadeev
 * @version 1.0
 * 
 * Diese Klasse beinhaltet alle Befehle, die beim Spiel
 * ausgeführt werden können.
 *
 */
public class Game {
    /**
     * Mithilfe dieses Stack wird die Anzahl der Züge gezählt,
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
     * Dieser boolean wird dafür verwendet, falls "place" fehlschlägt, um den Stack
     * der die Züge beinhaltet ordnungsgemäß im Falle des fehlgeschlagenen Befehls zu bearbeiten.
     */
    private boolean delete;    
    /**
     * Platzhalter der den in "select" ausgewählten Spielstein speichert.
     */
    private Stone current;
    /**
     * Konstruktor für die Game-Klasse.
     */
    public Game() { }
    /**
     * Diese Methode wählt einen Spielstein aus.
     * @param n
     * Dieser Int-Wert stellt den Spielstein dar,
     * der ausgewählt werden soll.
     * 
     * @throws InvalidStoneException
     * Wirft eine Exception falls, ein bestimmter Stein bereits schon einmal
     * ausgewählt wurde, oder falls momentan schon ein Stein ausgewählt ist.
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
     * Diese Methode beinhaltet den "place"-Befehl für den
     * Standard-Modus.
     * 
     * @param i
     * Diese Variable stellt die Zeile dar.
     * 
     * @param j
     * Diese Variable stellt die Spalte dar.
     * 
     * @return current
     * Gibt den Platzhalter für den Spielstein zurück.
     * 
     * @throws FieldException
     * Diese Exception wird geworfen wenn kein Stein ausgewählt
     * wurde, oder das ausgewählte Feld nicht frei ist.
     * 
     * @throws NumberFormatException
     * Diese Exception wird geworfen, wenn keine
     * natürliche Zahl als Parameter eingegeben wird.
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
     * Diese Methode beinhaltet die place-Methode für den Torus-Modus.
     * 
     * @param i
     * Diese Variable stellt die Zeile dar.
     * 
     * @param j
     * Diese Variable stellt die Spalte dar.
     * 
     * @return current
     * Gibt den Platzhalter für den Spielstein zurück.
     * 
     * @throws FieldException
     * Wird geworfen wenn kein Stein ausgewählt ist, oder
     * das ausgewählte Feld nicht leer ist.
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
     * Diese Methode ist dazu da einen String zu erzeugen der alle verfügbaren
     * Spielsteine, mittels eines Stringbuilder, ausgibt.
     * 
     * @return s
     * Gibt den String zurück, der alle verfügbaren Spielsteine beinhaltet.
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
     * Gibt den String zurück, der die Zeile beinhaltet.
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
     * Gibt den String zurück, der die Spalte beinhaltet.
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
     * Diese Methode setzt ein Spiel zurück, und wird immer dann ausgeführt
     * wenn eines neues Spiel gestartet wird, sprich bspw. alle Spielsteine sind wieder verfügbar,
     * das Feld ist wieder leer, die Züge werden zurückgesetzt etc.
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
     * Setter für den Platzhalter der Spielsteine.
     * @param st
     * Stellt den Wert dar, den "current" haben soll.
     */
    public void setCurrent(Stone st) {
        this.current = st;
    }
    /**
     * Getter für die Variable die verwendet wird,
     * falls "place" fehlschlägt.
     * @return delete
     * Gibt delete zurück.
     */
    public boolean getDelete() {
        return delete;
    }
    /**
     * Setter für die Variable die verwendet wird,
     * falls "place" fehlschlägt.
     * @param bl
     * Stellt den Wert dar, den "delete" haben soll.
     */
    public void setDelete(boolean bl) {
        this.delete = bl;
    }
    /**
     * Getter für den Stapel.
     * @return turns
     * Gibt den Stack zurück.
     */
    public Stack<String> getTurns() {
        return turns;
    }
    /**
     * Getter für das Spielfeld.
     * @return field
     * Gibt das Spielfeld zurück.
     */
    public Stone[][] getField() {
        return field;
    }
}
