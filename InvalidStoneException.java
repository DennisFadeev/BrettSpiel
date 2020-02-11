package edu.kit.informatik;
/**
 * 
 * @author Dennis Fadeev
 * @version 1.0
 * 
 * Eigene Exception für die select-Methode in der Game-Klasse.
 *
 */
public class InvalidStoneException extends Exception {
    /**
     * Konstruktor der die Exception beinhaltet.
     * 
     * @param message
     * Stellt die Nachricht dar.
     */
    public InvalidStoneException(String message) {
        super(message);
    }
}
