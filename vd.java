/*package edu.kit.informatik;

import java.util.HashMap;

public class CheckAlgorithm {
    static HashMap<Integer, Stone> winningStones = new HashMap<>();
    private final int first = 0;
    private final int last = 5;
    static boolean win = false;
    Game g;

    public CheckAlgorithm(Game g) {
        this.g = g;
    }

    public boolean check() {
        vertical();
        horizontal();
        diagOne();
        diagTwo();
        return win;
    }

    public boolean checkTorus() {
        horizonTorus();
        vertTorus();
        torusDiagOne();
        torusDiagTwo();        
        return win;
    }

    public boolean vertical() {
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 3; i++) {
                if ((g.field[i][j] != null) && (g.field[i + 1][j] != null) && (g.field[i + 2][j] != null)
                        && (g.field[i + 3][j] != null)) {
                    int k = i;
                    for (int n = 0; n < 4; n++) {
                        winningStones.put(n, g.field[k][j]);
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

    public boolean horizontal() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if ((g.field[i][j] != null) && (g.field[i][j + 1] != null) && (g.field[i][j + 2] != null)
                        && (g.field[i][j + 3] != null)) {
                    int k = j;
                    for (int n = 0; n < 4; n++) {
                        winningStones.put(n, g.field[i][k]);
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

    public boolean diagOne() {
        int n = 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                if ((g.field[i][j] != null) && (g.field[i + 1][j + 1] != null) && (g.field[i + 2][j + 2] != null)
                        && (g.field[i + 3][j + 3] != null)) {
                    int l = i;
                    int m = j;
                    for (int y = 0; y < 4; y++) {
                        winningStones.put(y, g.field[l][m]);
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

    public boolean diagTwo() {
        int n = 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 5; j >= n; j--) {
                if ((g.field[i][j] != null) && (g.field[i + 1][j - 1] != null) && (g.field[i + 2][j - 2] != null)
                        && (g.field[i + 3][j - 3] != null)) {
                    int l = i;
                    int m = j;
                    for (int y = 0; y < 4; y++) {
                        winningStones.put(y, g.field[l][m]);
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

    boolean horizonTorus() {
        for (int i = 0; i < 6; i++) {
            if ((g.field[i][first] != null) && (g.field[i][first + 1] != null) && (g.field[i][first + 2] != null)
                    && (g.field[i][last] != null)) {
                winningStones.put(0, g.field[i][first]);
                winningStones.put(1, g.field[i][first + 1]);
                winningStones.put(2, g.field[i][first + 2]);
                winningStones.put(3, g.field[i][last]);
                if (checkWin()) {
                    return win;
                } else {
                    winningStones.clear();
                }
            }

            if ((g.field[i][first] != null) && (g.field[i][first + 1] != null) && (g.field[i][last - 1] != null)
                    && (g.field[i][last] != null)) {
                winningStones.put(0, g.field[i][first]);
                winningStones.put(1, g.field[i][first + 1]);
                winningStones.put(2, g.field[i][last - 1]);
                winningStones.put(3, g.field[i][last]);
                if (checkWin()) {
                    return win;
                } else {
                    winningStones.clear();
                }
            }
            if ((g.field[i][first] != null) && (g.field[i][last] != null) && (g.field[i][last - 1] != null)
                    && (g.field[i][last - 2] != null)) {
                winningStones.put(0, g.field[i][first]);
                winningStones.put(1, g.field[i][last]);
                winningStones.put(2, g.field[i][last - 1]);
                winningStones.put(3, g.field[i][last - 2]);
                if (checkWin()) {
                    return win;
                } else {
                    winningStones.clear();
                }
            }
        }
        return win;
    }

    boolean vertTorus() {
        for (int j = 0; j < 6; j++) {
            if ((g.field[first][j] != null) && (g.field[first + 1][j] != null) && (g.field[first + 2][j] != null)
                    && (g.field[last][j] != null)) {
                winningStones.put(0, g.field[first][j]);
                winningStones.put(1, g.field[first + 1][j]);
                winningStones.put(2, g.field[first + 2][j]);
                winningStones.put(3, g.field[last][j]);
                if (checkWin()) {
                    return win;
                } else {
                    winningStones.clear();
                }
            }
            if ((g.field[first][j] != null) && (g.field[first + 1][j] != null) && (g.field[last - 1][j] != null)
                    && (g.field[last][j] != null)) {
                winningStones.put(0, g.field[first][j]);
                winningStones.put(1, g.field[first + 1][j]);
                winningStones.put(2, g.field[last - 1][j]);
                winningStones.put(3, g.field[last][j]);
                if (checkWin()) {
                    return win;
                } else {
                    winningStones.clear();
                }
            }
            if ((g.field[first][j] != null) && (g.field[last - 2][j] != null) && (g.field[last - 1][j] != null)
                    && (g.field[last][j] != null)) {
                winningStones.put(0, g.field[first][j]);
                winningStones.put(1, g.field[last - 2][j]);
                winningStones.put(2, g.field[last - 1][j]);
                winningStones.put(3, g.field[last][j]);
                if (checkWin()) {
                    return win;
                } else {
                    winningStones.clear();
                }
            }
        }
        return win;
    }

    boolean torusDiagOne() {
        for (int i = 5; i >= 0; i--) {
            for (int j = 5; j >= 0; j--) {
                if ((g.field[i][j] != null) && (g.field[(((i - 1) % 6) + 6) % 6][(((j + 1) % 6) + 6) % 6] != null)
                        && (g.field[(((i - 2) % 6) + 6) % 6][(((j + 2) % 6) + 6) % 6] != null)
                        && (g.field[(((i - 3) % 6) + 6) % 6][(((j + 3) % 6) + 6) % 6] != null)) {

                    winningStones.put(0, g.field[i][j]);
                    winningStones.put(1, g.field[(((i - 1) % 6) + 6) % 6][(((j + 1) % 6) + 6) % 6]);
                    winningStones.put(2, g.field[(((i - 2) % 6) + 6) % 6][(((j + 2) % 6) + 6) % 6]);
                    winningStones.put(3, g.field[(((i - 3) % 6) + 6) % 6][(((j + 3) % 6) + 6) % 6]);
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

    boolean torusDiagTwo() {
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if ((g.field[i][j] != null) && (g.field[(((i + 1) % 6) + 6) % 6][(((j + 1) % 6) + 6) % 6] != null)
                        && (g.field[(((i + 2) % 6) + 6) % 6][(((j + 2) % 6) + 6) % 6] != null)
                        && (g.field[(((i + 3) % 6) + 6) % 6][(((j + 3) % 6) + 6) % 6] != null)) {

                    winningStones.put(0, g.field[i][j]);
                    winningStones.put(1, g.field[(((i + 1) % 6) + 6) % 6][(((j + 1) % 6) + 6) % 6]);
                    winningStones.put(2, g.field[(((i + 2) % 6) + 6) % 6][(((j + 2) % 6) + 6) % 6]);
                    winningStones.put(3, g.field[(((i + 3) % 6) + 6) % 6][(((j + 3) % 6) + 6) % 6]);
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

    boolean checkWin() {
        if ((winningStones.get(0).color.equals(Color.BLACK)) && (winningStones.get(1).color.equals(Color.BLACK))
                && (winningStones.get(2).color.equals(Color.BLACK))
                && (winningStones.get(3).color.equals(Color.BLACK))) {
            win = true;
        }
        if ((winningStones.get(0).color.equals(Color.WHITE)) && (winningStones.get(1).color.equals(Color.WHITE))
                && (winningStones.get(2).color.equals(Color.WHITE))
                && (winningStones.get(3).color.equals(Color.WHITE))) {
            win = true;
        }
        if ((winningStones.get(0).shape.equals(Shape.SQUARE)) && (winningStones.get(1).shape.equals(Shape.SQUARE))
                && (winningStones.get(2).shape.equals(Shape.SQUARE))
                && (winningStones.get(3).shape.equals(Shape.SQUARE))) {
            win = true;
        }
        if ((winningStones.get(0).shape.equals(Shape.CYLINDRICALLY))
                && (winningStones.get(1).shape.equals(Shape.CYLINDRICALLY))
                && (winningStones.get(2).shape.equals(Shape.CYLINDRICALLY))
                && (winningStones.get(3).shape.equals(Shape.CYLINDRICALLY))) {
            win = true;
        }
        if ((winningStones.get(0).size.equals(Size.BIG)) && (winningStones.get(1).size.equals(Size.BIG))
                && (winningStones.get(2).size.equals(Size.BIG)) && (winningStones.get(3).size.equals(Size.BIG))) {
            win = true;
        }
        if ((winningStones.get(0).size.equals(Size.SMALL)) && (winningStones.get(1).size.equals(Size.SMALL))
                && (winningStones.get(2).size.equals(Size.SMALL)) && (winningStones.get(3).size.equals(Size.SMALL))) {
            win = true;
        }
        if ((winningStones.get(0).density.equals(Density.HOLLOW))
                && (winningStones.get(1).density.equals(Density.HOLLOW))
                && (winningStones.get(2).density.equals(Density.HOLLOW))
                && (winningStones.get(3).density.equals(Density.HOLLOW))) {
            win = true;
        }
        if ((winningStones.get(0).density.equals(Density.MASSIVE))
                && (winningStones.get(1).density.equals(Density.MASSIVE))
                && (winningStones.get(2).density.equals(Density.MASSIVE))
                && (winningStones.get(3).density.equals(Density.MASSIVE))) {
            win = true;
        }
        return win;
    }
}*/