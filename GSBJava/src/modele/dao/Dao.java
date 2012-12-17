package modele.dao;

import java.sql.*;
import modele.metier.Visiteur;

/**
 *
 * @author btssio
 * @version finale (après modification de la BD)
 */
public abstract class Dao {

    private String piloteJdbc;
    private String urlBd;
    private String loginBd;
    private String mdpBd;
    private Connection cnx;
    
    private PreparedStatement pstmtLireUnRapport;
    private PreparedStatement pstmtLireUnVisiteur;
   

    public Dao(String piloteJdbc, String urlBd, String loginBd, String mdpBd) {
        this.piloteJdbc = piloteJdbc;
        this.urlBd = urlBd;
        this.loginBd = loginBd;
        this.mdpBd = mdpBd;
    }

    public void connecter() throws DaoException {
        try {
            Class.forName(piloteJdbc);
            cnx = DriverManager.getConnection(urlBd, loginBd, mdpBd);
            
            pstmtLireUnRapport = cnx.prepareStatement(
                    "SELECT * FROM RAPPORT_VISITE WHERE VIS_MATRICULE=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            pstmtLireUnVisiteur = cnx.prepareStatement("SELECT * FROM VISITEUR WHERE VIS_MATRICULE=?");
           
        } catch (SQLException ex) {
            throw new DaoException("DAO - connecter : pb de connexion\n" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            throw new DaoException("DAO - connecter : pb de pilote JDBC\n" + ex.getMessage());
        }
    }

    public void deconnecter() throws DaoException {
        try {
            cnx.close();
        } catch (SQLException ex) {
            throw new DaoException("DAO - déconnecter : pb JDBC\n" + ex.getMessage());
        }
    }
    
    public Visiteur lireUnVisiteur(int id) throws DaoException {
        try {
            Visiteur visiteur = null;
            pstmtLireUnVisiteur.setInt(1, id);
            ResultSet rs = pstmtLireUnVisiteur.executeQuery();
            if (rs.next()) {
                visiteur = chargerUnEnregistrementVisiteur(rs);
            }
            return visiteur;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireUnVisiteur : pb JDBC\n" + ex.getMessage());
        }
    }
    
    private Visiteur chargerUnEnregistrementVisiteur(ResultSet rs) throws DaoException {
        try {
            Visiteur visiteur = new Visiteur();
            visiteur.setNom(rs.getString("VIS_NOM"));
            visiteur.setPrenom(rs.getString("VIS_PRENOM"));
            visiteur.setAdresse(rs.getString("VIS_ADRESSE"));
            
            return visiteur;
        } catch (SQLException ex) {
            throw new DaoException("DAO - chargerUnEnregistrementVisiteur : pb JDBC\n" + ex.getMessage());
        }
    }
}
