/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

/**
 *
 * @author btssio
 */
public class DaoOracle extends Dao{

    public DaoOracle(String nomBd, String loginBd, String mdpBd) {
        super("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:localhost/GSB@//localhost:1521/xe", loginBd, mdpBd);
    }
    
}
