/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

/**
 *
 * @author btssio
 */
public class DaoMySql extends Dao{

    public DaoMySql(String nomBd, String loginBd, String mdpBd) {
        super("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/"+nomBd, loginBd, mdpBd);
    }
    
}
