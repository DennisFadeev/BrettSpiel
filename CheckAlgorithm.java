package edu.kit.informatik;

import java.util.HashMap;
/**
 * 
 * @author Dennis Fadeev
 * @version 1.0
 * 
 * Diese Klasse dient dazu, zu ermitteln ob ein Spieler gewonnen hat, bzw.
 * ob vier Steine mit mindestens einer gemeinsamen Eigenschaft, horizontal, vertikal oder diagonal
 * in einer Reihe platziert wurden, sowohl im Standard-Modus als auch im "Torus"-Modus.
 *
 */
public class CheckAlgorithm {
    /**
     * Gibt zurück ob das Spiel beendet ist, bzw. ob jemand gewonnen hat.
     */
    private static boolean win = false;
    /**
     * Falls vier Steine in einer Reihe gefunden wurden, werden sie zu dieser HashMap hinzugefügt
     * und werden mittels der "checkWin"-Methode auf gleiche Eigenschaften überprüft-
     */
    private static HashMap<Integer, Stone> winningStones = new HashMap<>();
    /**
     * Erstellt eine Instanz der Game-Klasse.
     */
    private Game g;
    /**
     * Konstruktor der die Instanz der Game-Klasse verwendet.
     * @param g
     * Konstruktor mit einer Instanz der Game-Klasse als Parameter.
     */
    public CheckAlgorithm(Game g) {
        this.g = g;
    }
    /**
     * Hier werden alle Methoden für die Ermittlung des Gewinners
     * im Standard-Modus "zusammengefügt".
     * 
     * @return win
     * Gibt zurück ob das Spiel beendet ist oder nicht.
     */
    public boolean check() {
        vertical();
        horizontal();
        diagOne();
        diagTwo();
        return win;
    }
    /**
     * Hier werde alle Methoden für die Ermittlung des Gewinners
     * im Torus-Modus "zusammengefügt".
     * @return win
     * Gibt zurück ob das Spiel beendet ist oder nicht.
     */
    public boolean checkTorus() {
        horizonTorus();
        vertTorus();
        torusDiagOne();
        torusDiagTwo();
        return win;
    }
    /**
     * Ermittelt ob vier Steine in einer Vertikalen, die selben Eigenschaften haben.
     * @return win
     * Gibt zurück ob das Spiel beendet ist oder nicht.
     */
    public boolean vertical() {
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 3; i++) {
                if ((g.getField()[i][j] != null) && (g.getField()[i + 1][j] != null) && (g.getField()[i + 2][j] != null)
                        && (g.getField()[i + 3][j] != null)) {
                    int k = i;
                    for (int n = 0; n < 4; n++) {
                        winningStones.put(n, g.getField()[k][j]);
                        k++;
                    }
                    if (checkWin()) {
                        return win;
                    } else {
                        winningStones.clear();
                    }
                }
            }
        }
        return win;
    }
    /**
     * Ermittelt ob vier Steine in einer Horizontalen, die selben Eigenschaften haben.
     * @return win
     * Gibt zurück ob das Spiel beendet ist oder nicht.
     */
    public boolean horizontal() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if ((g.getField()[i][j] != null) && (g.getField()[i][j + 1] != null) && (g.getField()[i][j + 2] != null)
                        && (g.getField()[i][j + 3] != null)) {
                    int k = j;
                    for (int n = 0; n < 4; n++) {
                        winningStones.put(n, g.getField()[i][k]);
                        k++;
                    }
                    if (checkWin()) {
                        return win;
                    } else {
                        winningStones.clear();
                    }
                }
            }
        }
        return win;
    }
    /**
     * Ermittelt ob vier Steine in einer Diagonalen-Richtung, die selben Eigenschaften haben.
     * @return win
     * Gibt zurück ob das Spiel beendet ist oder nicht.
     */
    public boolean diagOne() {
        int n = 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                if ((g.getField()[i][j] != null)
                        && (g.getField()[i + 1][j + 1] != null)
                        && (g.getField()[i + 2][j + 2] != null)
                        && (g.getField()[i + 3][j + 3] != null)) {
                    int l = i;
                    int m = j;
                    for (int y = 0; y < 4; y++) {
                        winningStones.put(y, g.getField()[l][m]);
                        l++;
                        m++;
                    }
                    if (checkWin()) {
                        return win;
                    } else {
                        winningStones.clear();
                    }
                }
            }
        }
        return win;
    }
    /**
     * Ermittelt ob vier Steine in der anderen Diagonalen-Richtung, die selben Eigenschaften haben.
     * @return win
     * Gibt zurück ob das Spiel beendet ist oder nicht.
     */
    public boolean diagTwo() {
        int n = 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 5; j >= n; j--) {
                if ((g.getField()[i][j] != null)
                        && (g.getField()[i + 1][j - 1] != null)
                        && (g.getField()[i + 2][j - 2] != null)
                        && (g.getField()[i + 3][j - 3] != null)) {
                    int l = i;
                    int m = j;
                    for (int y = 0; y < 4; y++) {
                        winningStones.put(y, g.getField()[l][m]);
                        l++;
                        m--;
                    }
                    if (checkWin()) {
                        return win;
                    } else {
                        winningStones.clear();
                    }
                }
            }
        }
        return win;
    }
    /**
     * Ermittelt ob vier Steine in einer Horizontalen, im Torus-Modus, die selben Eigenschaften haben.
     * @return win
     * Gibt zurück ob das Spiel beendet ist oder nicht.
     */
    boolean horizonTorus() {
        for (int i = 0; i < 6; i++) {
            for (int j = 2; j >= 0; j--) {
                if ((g.getField()[i][j] != null)
                        && (g.getField()[i][(((j - 1) % 6) + 6) % 6] != null)
                        && (g.getField()[i][(((j - 2) % 6) + 6) % 6] != null)
                        && (g.getField()[i][((j - 3) % 6 + 6) % 6] != null)) {
                    winningStones.put(0, g.getField()[i][j]);
                    winningStones.put(1, g.getField()[i][(((j - 1) % 6) + 6) % 6]);
                    winningStones.put(2, g.getField()[i][(((j - 2) % 6) + 6) % 6]);
                    winningStones.put(3, g.getField()[i][(((j - 3) % 6) + 6) % 6]);
                    if (checkWin()) {
                        return win;
                    } else {
                        winningStones.clear();
                    }
                }
            }
        }
        return win;
    }
    /**
     * Ermittelt ob vier Steine in einer Vertikalen, im Torus-Modus, die selben Eigenschaften haben.
     * @return win
     * Gibt zurück ob das Spiel beendet ist oder nicht.
     */
    boolean vertTorus() {
        for (int j = 0; j < 6; j++) {
            for (int i = 2; i >= 0; i--) {
                if ((g.getField()[i][j] != null)
                        && (g.getField()[(((i - 1) % 6) + 6) % 6][j] != null)
                        && (g.getField()[(((i - 2) % 6) + 6) % 6][j] != null)
                        && (g.getField()[(((i - 3) % 6) + 6) % 6][j] != null)) {
                    winningStones.put(0, g.getField()[i][j]);
                    winningStones.put(1, g.getField()[(((i - 1) % 6) + 6) % 6][j]);
                    winningStones.put(2, g.getField()[(((i - 2) % 6) + 6) % 6][j]);
                    winningStones.put(3, g.getField()[(((i - 3) % 6) + 6) % 6][j]);
                    if (checkWin()) {
                        return win;
                    } else {
                        winningStones.clear();
                    }
                }
            }
        }
        return win;
    }
    /**
     * Ermittelt ob vier Steine in einer Diagonalen-Richtung, im Torus-Modus, die selben Eigenschaften haben.
     * @return win
     * Gibt zurück ob das Spiel beendet ist oder nicht.
     */
    boolean torusDiagOne() {
        for (int i = 5; i >= 0; i--) {
            for (int j = 5; j >= 0; j--) {
                if ((g.getField()[i][j] != null)
                        && (g.getField()[(((i - 1) % 6) + 6) % 6][(((j + 1) % 6) + 6) % 6] != null)
                        && (g.getField()[(((i - 2) % 6) + 6) % 6][(((j + 2) % 6) + 6) % 6] != null)
                        && (g.getField()[(((i - 3) % 6) + 6) % 6][(((j + 3) % 6) + 6) % 6] != null)) {

                    winningStones.put(0, g.getField()[i][j]);
                    winningStones.put(1, g.getField()[(((i - 1) % 6) + 6) % 6][(((j + 1) % 6) + 6) % 6]);
                    winningStones.put(2, g.getField()[(((i - 2) % 6) + 6) % 6][(((j + 2) % 6) + 6) % 6]);
                    winningStones.put(3, g.getField()[(((i - 3) % 6) + 6) % 6][(((j + 3) % 6) + 6) % 6]);
                    if (checkWin()) {
                        return win;
                    } else {
                        winningStones.clear();
                    }
                }
            }
        }
        return win;
    }
    /**
     * Ermittelt ob vier Steine in der anderen Diagonalen-Richtung, im Torus-Modus, die selben Eigenschaften haben.
     * @return win
     * Gibt zurück ob das Spiel beendet ist oder nicht.
     */
    boolean torusDiagTwo() {
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if ((g.getField()[i][j] != null)
                        && (g.getField()[(((i + 1) % 6) + 6) % 6][(((j + 1) % 6) + 6) % 6] != null)
                        && (g.getField()[(((i + 2) % 6) + 6) % 6][(((j + 2) % 6) + 6) % 6] != null)
                        && (g.getField()[(((i + 3) % 6) + 6) % 6][(((j + 3) % 6) + 6) % 6] != null)) {

                    winningStones.put(0, g.getField()[i][j]);
                    winningStones.put(1, g.getField()[(((i + 1) % 6) + 6) % 6][(((j + 1) % 6) + 6) % 6]);
                    winningStones.put(2, g.getField()[(((i + 2) % 6) + 6) % 6][(((j + 2) % 6) + 6) % 6]);
                    winningStones.put(3, g.getField()[(((i + 3) % 6) + 6) % 6][(((j + 3) % 6) + 6) % 6]);
                    if (checkWin()) {
                        return win;
                    } else {
                        winningStones.clear();
                    }
                }
            }
        }
        return win;
    }
    /**
     * Hier werden die Steine, die mittels der oberen Methoden darauf überprüft wurden,
     * ob sie in einer Vierer-Reihe vorliegen,
     * @return win
     * Gibt zurück ob das Spiel beendet ist, oder nicht.
     */
    boolean checkWin() {
        if ((winningStones.get(0).getColor().equals(Color.BLACK))
                && (winningStones.get(1).getColor().equals(Color.BLACK))
                && (winningStones.get(2).getColor().equals(Color.BLACK))
                && (winningStones.get(3).getColor().equals(Color.BLACK))) {
            win = true;
        }
        if ((winningStones.get(0).getColor().equals(Color.WHITE))
                && (winningStones.get(1).getColor().equals(Color.WHITE))
                && (winningStones.get(2).getColor().equals(Color.WHITE))
                && (winningStones.get(3).getColor().equals(Color.WHITE))) {
            win = true;
        }
        if ((winningStones.get(0).getShape().equals(Shape.SQUARE))
                && (winningStones.get(1).getShape().equals(Shape.SQUARE))
                && (winningStones.get(2).getShape().equals(Shape.SQUARE))
                && (winningStones.get(3).getShape().equals(Shape.SQUARE))) {
            win = true;
        }
        if ((winningStones.get(0).getShape().equals(Shape.CYLINDRICALLY))
                && (winningStones.get(1).getShape().equals(Shape.CYLINDRICALLY))
                && (winningStones.get(2).getShape().equals(Shape.CYLINDRICALLY))
                && (winningStones.get(3).getShape().equals(Shape.CYLINDRICALLY))) {
            win = true;
        }
        if ((winningStones.get(0).getSize().equals(Size.BIG))
                && (winningStones.get(1).getSize().equals(Size.BIG))
                && (winningStones.get(2).getSize().equals(Size.BIG))
                && (winningStones.get(3).getSize().equals(Size.BIG))) {
            win = true;
        }
        if ((winningStones.get(0).getSize().equals(Size.SMALL))
                && (winningStones.get(1).getSize().equals(Size.SMALL))
                && (winningStones.get(2).getSize().equals(Size.SMALL))
                && (winningStones.get(3).getSize().equals(Size.SMALL))) {
            win = true;
        }
        if ((winningStones.get(0).getDensity().equals(Density.HOLLOW))
                && (winningStones.get(1).getDensity().equals(Density.HOLLOW))
                && (winningStones.get(2).getDensity().equals(Density.HOLLOW))
                && (winningStones.get(3).getDensity().equals(Density.HOLLOW))) {
            win = true;
        }
        if ((winningStones.get(0).getDensity().equals(Density.MASSIVE))
                && (winningStones.get(1).getDensity().equals(Density.MASSIVE))
                && (winningStones.get(2).getDensity().equals(Density.MASSIVE))
                && (winningStones.get(3).getDensity().equals(Density.MASSIVE))) {
            win = true;
        }
        return win;
    }
    /**
     * Getter für die HashMap, in der die Eigenschaften
     * der Steine verglichen werden.
     * @return winningStones
     * Gibt die HashMap zurück.
     */
    public static HashMap<Integer, Stone> getWinningStones() {
        return winningStones;
    }
    /**
     * Setter für den boolean der entscheidet, ob
     * das Spiel vorbei ist oder nicht.
     * @param bl
     * Ändert den Zustand von win.
     */
    public static void setWin(boolean bl) {
        win = bl;
    }
}