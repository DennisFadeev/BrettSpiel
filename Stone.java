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
     * Dieser boolean dient dazu, dass Spielsteine nicht mehr ausgewählt werden können
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
     * Integer für die Nummer der Spielsteine.
     */
    private int id;
    /**
     * Konstruktor für die Spielsteine.
     * 
     * @param color
     * Stellt die Farbe des Steines dar.
     * 
     * @param shape
     * Stellt die Form des Steines dar.
     * 
     * @param size
     * Stellt die Größe des Steines dar.
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
     * Getter-Methode für die Nummer des Steines.
     * @return id
     * Gibt die Nummer des Spielsteines zurück.
     */
    public int getId() {
        return id;
    }
    /**
     * Getter für die Farbe der Steine.
     * @return color
     * Gibt die Farbe zurück.
     */
    public Color getColor() {
        return color;
    }
    /**
     * Getter für die Form der Steine.
     * @return shape
     * Gibt die Form zurück.
     */
    public Shape getShape() {
        return shape;
    }
    /**
     * Getter für die Größe der Steine.
     * @return size
     * Gibt die Größe zurück.
     */
    public Size getSize() {
        return size;
    }
    /**
     * Getter für die Dichte der Steine.
     * @return density
     * Gibt die Dichte zurück.
     */
    public Density getDensity() {
        return density;
    }
    /**
     * Getter für den boolean, der die Verfügbarkeit
     * von Spielsteinen ändert.
     * @return free
     * Gibt den boolean für die Verfügbarkeit zurück.
     */
    public boolean getFree() {
        return free;
    }
    /**
     * Setter für den boolean, der die Verfügbarkeit
     * von Spielsteinen ändert.
     * @param bl
     * Setzt den boolean für die Verfügbarkeit der
     * Spielsteine auf einen neuen Wert.
     */
    public void setFree(boolean bl) {
        this.free = bl;
    }
}
