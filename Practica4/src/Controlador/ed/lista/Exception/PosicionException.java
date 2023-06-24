/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Controlador.ed.lista.Exception;

/**
 *
 * @author alejandro
 */
public class PosicionException extends Exception{

    /**
     * Creates a new instance of <code>PosicionException</code> without detail
     * message.
     */
    public PosicionException() {
        super("No existe esa posición dentro de la lista");
    }

    /**
     * Constructs an instance of <code>PosicionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PosicionException(String msg) {
        super(msg);
    }
}
