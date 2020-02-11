package edu.kit.informatik;

/**
 * 
 * @author Dennis Fadeev
 * @version 1.0
 * 
 *          Main-Klasse die die in der Game-Klasse erzeugten Methoden umsetzt.
 *
 */
public final class UI {
    /**
     * Mit diesem boolean wird entschieden, ob andere Befehle als "start ..."
     * eingegeben werden dürfen, sprich ob ein Spiel gestartet wurde oder nicht.
     */
    private static boolean start = false;
    /**
     * Dieser boolean entscheidet ob, "place" und "select" eingegeben werden dürfen,
     * sprich ob das Spiel zuende ist oder nicht.
     */
    private static boolean end = false;
    /**
     * Dieser boolean differenziert zwischen dem Torus- und dem Standard-Modus, und
     * führt den entsprechenden "place"-Befehl aus.
     */
    private static boolean mode = true;
    /**
     * Erstellt eine neue Instanz der Game-Klasse.
     */
    private static Game g = new Game();
    /**
     * Erstellt eine neue Instanz der CheckAlgorithm-Klasse mit der Game-Instanz als
     * Parameter.
     */
    private static CheckAlgorithm c = new CheckAlgorithm(g);

    /**
     * Privater Konstruktor, weil es eine Utility-Klasse ist.
     */
    private UI() {
    }

    /**
     * Main-Methode
     * 
     * @param arg Standardmäßiger Parameter für die Main-methode.
     * 
     */
    public static void main(String[] arg) {

        String input = Terminal.readLine();

        while (!input.equals("quit")) {

            if (input.equals("bag")) {

                if (start == true) {
                    Terminal.printLine(g.bag());
                } else {
                    Terminal.printError("Choose a gamemode!");
                }

            } else {
                String[] parts = input.split(" ");

                if (parts.length == 2) {

                    String command = parts[0];
                    String rightSide = parts[1];
                    if (command.equals("start") || command.equals("select")) {
                        handleCommand(command, rightSide);
                    } else if (command.equals("place")) {
                        handleCommand2(command, rightSide);
                    } else {
                        handleCommand3(command, rightSide);
                    }
                } else {
                    Terminal.printError("Invalid arguments!");
                }
            }

            input = Terminal.readLine();
        }
    }

    /**
     * Diese Methode beinhaltet "start" und "select", und wird nur aufgeteilt, weil
     * das Methodenzeilen-Limit in der Main-Klasse 80!-Zeilen beträgt, obwohl es
     * nicht die Main-Methode ist.
     * 
     * @param command   Dieser String stellt die "linke Seite" der Eingabe dar, also
     *                  den Befehl.
     * 
     * @param rightSide Dieser String stellt die "rechte Seite" der Eingabe dar,
     *                  also die Parameter wie z.b. der Stein der ausgewählt werden
     *                  soll bei "select".
     */

    private static void handleCommand(String command, String rightSide) {
        switch (command) {

        case "start":

            if (rightSide.equals("standard")) {
                g.reset();
                mode = true;
                start = true;
                end = false;
                Terminal.printLine("OK");
                break;
            } else if (rightSide.equals("torus")) {
                g.reset();
                mode = false;
                start = true;
                end = false;
                Terminal.printLine("OK");
                break;
            } else {
                Terminal.printError("This is an invalid gamemode!");
                break;
            }

        case "select":
            if (!start) {
                Terminal.printError("Choose a gamemode!");
                break;
            }

            if (!end) {
                try {
                    int n = Integer.parseInt(rightSide);
                    g.select(n);
                } catch (InvalidStoneException e) {
                    Terminal.printError(e.getMessage());
                    break;

                } catch (ArrayIndexOutOfBoundsException e) {
                    Terminal.printError(e.getMessage());
                    break;

                } catch (NumberFormatException e) {
                    Terminal.printError("Invalid number format!");
                    break;
                }
                Terminal.printLine("OK");
                break;
            } else {
                Terminal.printError("Start a new Game!");
                break;
            }
        default:
            Terminal.printError(String.format("Unknown command '%s'.", command + " " + rightSide));
            break;

        }
    }

    /**
     * Diese Methode beinhaltet "rowprint" und "colprint", und wird nur aufgeteilt,
     * weil das Methodenzeilen-Limit in der Main-Klasse 80!-Zeilen beträgt, obwohl
     * es nicht die Main-Methode ist.
     * 
     * @param command   Dieser String stellt die "linke Seite" der Eingabe dar, also
     *                  den Befehl.
     * 
     * @param rightSide Dieser String stellt die "rechte Seite" der Eingabe dar,
     *                  also die Parameter wie z.b. der Stein der ausgewählt werden
     *                  soll bei "select".
     */
    private static void handleCommand3(String command, String rightSide) {
        switch (command) {
        case "rowprint":
            if (!start) {
                Terminal.printError("Choose a gamemode!");
                break;
            }
            try {
                int row = Integer.parseInt(rightSide);
                Terminal.printLine(g.rowprint(row));
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                Terminal.printError("This row doesn't exist!");
                break;

            } catch (NumberFormatException e) {
                Terminal.printError("Invalid Number Format");
                break;
            }

        case "colprint":
            if (!start) {
                Terminal.printError("Choose a gamemode!");
                break;
            }
            try {
                int col = Integer.parseInt(rightSide);
                Terminal.printLine(g.colprint(col));
                break;

            } catch (ArrayIndexOutOfBoundsException ex) {
                Terminal.printError("This column doesn't exist!");
                break;
            }
        default:
            Terminal.printError(String.format("Unknown command '%s'.", command + " " + rightSide));
            break;
        }
    }

    /**
     * Diese Methode beinhaltet "place", und wird nur aufgeteilt, weil das
     * Methodenzeilen-Limit in der Main-Klasse 80!-Zeilen beträgt, obwohl es nicht
     * die Main-Methode ist.
     * 
     * @param command   Dieser String stellt die "linke Seite" der Eingabe dar, also
     *                  den Befehl.
     * 
     * @param rightSide Dieser String stellt die "rechte Seite" der Eingabe dar,
     *                  also die Parameter wie z.b. der Stein der ausgewählt werden
     *                  soll bei "select".
     */
    private static void handleCommand2(String command, String rightSide) {
        switch (command) {
        case "place":
            String[] args = rightSide.split(";");

            if (args.length != 2) {
                Terminal.printError("Invalid arguments");
                break;
            }
            if (!start) {
                Terminal.printError("Choose a gamemode!");
                break;
            }
            if (!end) {
                try {
                    int i = Integer.parseInt(args[0]);
                    int j = Integer.parseInt(args[1]);

                    if (mode == true) {
                        g.place(i, j);
                        if (c.check()) {
                            Terminal.printLine(g.getTurns().peek() + " wins");
                            Terminal.printLine(g.getTurns().size() - 1);
                            end = true;
                            break;
                        }
                        if (!c.check() && g.getTurns().size() == 16) {
                            Terminal.printLine("draw");
                            end = true;
                            break;
                        }
                        Terminal.printLine("OK");
                        break;
                    } else if (mode == false) {
                        g.placeTorus(i, j);
                        if (c.checkTorus() || c.check()) {
                            Terminal.printLine(g.getTurns().peek() + " wins");
                            Terminal.printLine(g.getTurns().size() - 1);
                            end = true;
                            break;
                        }
                        if (!c.check() && g.getTurns().size() == 16) {
                            Terminal.printLine("draw");
                            end = true;
                            break;
                        }
                        Terminal.printLine("OK");
                        break;
                    }
                } catch (NumberFormatException e) {
                    placeFail();
                    Terminal.printError("Invalid Number Format!");
                    break;
                } catch (FieldException e) {
                    placeFail();
                    Terminal.printError(e.getMessage());
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    placeFail();
                    Terminal.printError("Pick a valid Field!");
                    break;
                }
                Terminal.printLine("OK");
                break;
            } else {
                Terminal.printError("Start a new Game!");
                break;
            }
        default:
            Terminal.printError(String.format("Unknown command '%s'.", command + " " + rightSide));
            break;
        }
    }

    /**
     * Diese Methode bearbeitet unter anderem den Stapel der die Züge, bzw. die
     * Spieler-Rotation beinhaltet, falls "place" fehlschlägt.
     */
    private static void placeFail() {
        g.setCurrent(null);
        if (g.getTurns().size() > 1 && g.getDelete() == true) {
            String top = g.getTurns().peek();
            g.getTurns().pop();
            String second = g.getTurns().peek();
            g.getTurns().pop();
            g.getTurns().push(top);
            g.getTurns().push(second);
            g.setDelete(false);
        } else {
            if (g.getDelete() == true && g.getTurns().size() != 0) {
                g.getTurns().pop();
                g.getTurns().push("P1");
            }
        }
    }
}
