package modele.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modele.metier.Visiteur;
import vues.VueVisiteur;

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
    private PreparedStatement pstmtlireTousLesVisiteurs;
   

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
            pstmtlireTousLesVisiteurs = cnx.prepareStatement("SELECT * FROM VISITEUR", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
           
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
    
    public Visiteur lireUnVisiteur(String matricule) throws DaoException {
        try {
            Visiteur visiteur = null;
            pstmtLireUnVisiteur.setString(1, matricule);
            ResultSet rs = pstmtLireUnVisiteur.executeQuery();
            if (rs.next()) {
                visiteur = chargerUnEnregistrementVisiteur(rs);
            }
            return visiteur;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireUnVisiteur : pb JDBC\n" + ex.getMessage());
        }
    }
    
            
    public List<Visiteur> lireTousLesVisiteurs() throws DaoException{
        try {
            List<Visiteur> desVisiteurs = new ArrayList<Visiteur>();
            ResultSet rs = pstmtlireTousLesVisiteurs.executeQuery();
            while (rs.next()) {
                Visiteur unVisiteur = chargerUnEnregistrementVisiteur(rs);
                desVisiteurs.add(unVisiteur);
            }            
            return desVisiteurs;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireTousLesVisiteurs : pb JDBC\n" + ex.getMessage());
        }
    }
    
    private Visiteur chargerUnEnregistrementVisiteur(ResultSet rs) throws DaoException {
        try {
            
            Visiteur visiteur = new Visiteur();
            visiteur.setMatricule(rs.getString("VIS_MATRICULE"));
            visiteur.setNom(rs.getString("VIS_NOM"));
            visiteur.setPrenom(rs.getString("VIS_PRENOM"));
            visiteur.setAdresse(rs.getString("VIS_ADRESSE"));
            visiteur.setVille(rs.getString("VIS_VILLE"));
            visiteur.setCp(rs.getString("VIS_CP"));
            visiteur.setDateEmbauche(rs.getString("VIS_DATEEMBAUCHE"));
            visiteur.setCodeLabo(rs.getString("LAB_CODE"));
            visiteur.setCodeSecteur(rs.getString("SEC_CODE"));
                        
            return visiteur;
        } catch (SQLException ex) {
            throw new DaoException("DAO - chargerUnEnregistrementVisiteur : pb JDBC\n" + ex.getMessage());
        }
    }
}
