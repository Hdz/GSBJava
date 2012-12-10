/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

/**
 *
 * @author btssio
 */
public class DaoException extends Exception {

    /**
     * Creates a new instance of <code>DaoException</code> without detail message.
     */
    public DaoException() {
    }

    /**
     * Constructs an instance of <code>DaoException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DaoException(String msg) {
        super(msg);
    }
}
