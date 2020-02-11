package edu.kit.informatik;
/**
 * 
 * @author Dennis Fadeev
 * @version 1.0
 * 
 * Eigene Exception für die place- bzw. placeTorus-Methode in der Game-Klasse.
 *
 */
public class FieldException extends Exception {
    /**
     * Konstruktor der die Exception beinhaltet.
     * 
     * @param message
     * Stellt die Nachricht dar.
     */
    public FieldException(String message) {
        super(message);
    }
}