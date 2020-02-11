package edu.kit.informatik;
/**
 * 
 * @author Dennis Fadeev
 * @version 1.0
 * 
 * Diese Klasse dient der Deklarierung und Generierung der Spielsteine.
 *
 */
public class Stone {
    /**
     * Dieser boolean dient dazu, dass Spielsteine nicht mehr ausgew�hlt werden k�nnen
     * wenn sie vorher bereits schon platziert wurden.
     */
    private boolean free = true;
    /**
     * Variable der Enum-Klasse "Color".
     */
    private Color color;
    /**
     * Variable der Enum-Klasse "Shape".
     */
    private Shape shape;
    /**
     * Variable der Enum-Klasse "Size".
     */
    private Size size;
    /**
     * Variable der Enum-Klasse "Density".
     */
    private Density density;
    /**
     * Integer f�r die Nummer der Spielsteine.
     */
    private int id;
    /**
     * Konstruktor f�r die Spielsteine.
     * 
     * @param color
     * Stellt die Farbe des Steines dar.
     * 
     * @param shape
     * Stellt die Form des Steines dar.
     * 
     * @param size
     * Stellt die Gr��e des Steines dar.
     * 
     * @param density
     * Stellt die Dichte des Steines dar.
     * 
     * @param id
     * Stellt die Nummer des Steines dar.
     */
    public Stone(Color color, Shape shape, Size size, Density density, int id) {
        this.color = color;
        this.shape = shape;
        this.size = size;
        this.density = density;
        this.id = id;
    }
    /**
     * Getter-Methode f�r die Nummer des Steines.
     * @return id
     * Gibt die Nummer des Spielsteines zur�ck.
     */
    public int getId() {
        return id;
    }
    /**
     * Getter f�r die Farbe der Steine.
     * @return color
     * Gibt die Farbe zur�ck.
     */
    public Color getColor() {
        return color;
    }
    /**
     * Getter f�r die Form der Steine.
     * @return shape
     * Gibt die Form zur�ck.
     */
    public Shape getShape() {
        return shape;
    }
    /**
     * Getter f�r die Gr��e der Steine.
     * @return size
     * Gibt die Gr��e zur�ck.
     */
    public Size getSize() {
        return size;
    }
    /**
     * Getter f�r die Dichte der Steine.
     * @return density
     * Gibt die Dichte zur�ck.
     */
    public Density getDensity() {
        return density;
    }
    /**
     * Getter f�r den boolean, der die Verf�gbarkeit
     * von Spielsteinen �ndert.
     * @return free
     * Gibt den boolean f�r die Verf�gbarkeit zur�ck.
     */
    public boolean getFree() {
        return free;
    }
    /**
     * Setter f�r den boolean, der die Verf�gbarkeit
     * von Spielsteinen �ndert.
     * @param bl
     * Setzt den boolean f�r die Verf�gbarkeit der
     * Spielsteine auf einen neuen Wert.
     */
    public void setFree(boolean bl) {
        this.free = bl;
    }
}
