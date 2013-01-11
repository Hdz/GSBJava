package modele.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modele.metier.Labo;
import modele.metier.Praticien;
import modele.metier.Rapport;
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
    private PreparedStatement pstmtLireTousLesRapports;
    private PreparedStatement pstmtAjouterUnRapport;
    private PreparedStatement pstmtLireNomFromCodeLabo;
    private PreparedStatement pstmtLireLibelleFomCodeSecteur;
    private PreparedStatement pstmtLireNomFromNum;
    private PreparedStatement pstmtLireNomFromMatriculeVisiteur;
    private PreparedStatement pstmtLireTousLesPraticiens;
    private PreparedStatement pstmtRecupererDernierNumRap;
    
   

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
            
            pstmtLireUnRapport = cnx.prepareStatement("SELECT * FROM RAPPORT_VISITE WHERE RAP_NUM=?");
            pstmtLireUnVisiteur = cnx.prepareStatement("SELECT * FROM VISITEUR WHERE VIS_MATRICULE=?");
            pstmtLireNomFromCodeLabo = cnx.prepareStatement("SELECT LAB_NOM FROM LABO WHERE LAB_CODE=?");
            pstmtlireTousLesVisiteurs = cnx.prepareStatement("SELECT * FROM VISITEUR", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstmtLireTousLesRapports = cnx.prepareStatement("SELECT * FROM RAPPORT_VISITE", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstmtLireLibelleFomCodeSecteur = cnx.prepareStatement("SELECT SEC_LIBELLE FROM SECTEUR WHERE SEC_CODE=?");
            pstmtLireNomFromNum = cnx.prepareStatement("SELECT PRA_NOM FROM PRATICIEN WHERE PRA_NUM=?");
            pstmtLireNomFromMatriculeVisiteur = cnx.prepareStatement("SELECT VIS_NOM FROM VISITEUR WHERE VIS_MATRICULE =?");
            pstmtAjouterUnRapport = cnx.prepareStatement("INSERT INTO RAPPORT_VISITE (VIS_MATRICULE, RAP_NUM, PRA_NUM, RAP_DATE, RAP_BILAN, RAP_MOTIF)"
                    + "VALUES (?,?,?,?,?,?)");
            pstmtLireTousLesPraticiens = cnx.prepareStatement("SELECT * FROM PRATICIEN", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstmtRecupererDernierNumRap = cnx.prepareStatement("SELECT max(RAP_NUM) FROM RAPPORT_VISITE");
            
           
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
    
    public Rapport lireUnRapport(String numRapport) throws DaoException {
        try {
            Rapport rapport = null;
            pstmtLireUnRapport.setString(1, numRapport);
            ResultSet rs = pstmtLireUnRapport.executeQuery();
            if (rs.next()) {
                rapport = chargerUnEnregistrementRapport(rs);
            }
            return rapport;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireUnRapport : pb JDBC\n" + ex.getMessage());
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
    
    public List<Praticien> lireTousLesPraticiens() throws DaoException{
        try {
            List<Praticien> desPraticiens = new ArrayList<Praticien>();
            ResultSet rs = pstmtLireTousLesPraticiens.executeQuery();
            while (rs.next()) {
                Praticien unPraticien = chargerUnEnregistrementPraticien(rs);
                desPraticiens.add(unPraticien);
            }            
            return desPraticiens;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireTousLesVisiteurs : pb JDBC\n" + ex.getMessage());
        }
    }
    
    public String lireNomFromCodeLabo(String code) throws DaoException{
        try {                    
            pstmtLireNomFromCodeLabo.setString(1, code);
            ResultSet rs = pstmtLireNomFromCodeLabo.executeQuery();
            if (rs.next())
                code = rs.getString(1);
            
            return code;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireNomFromCodeLabo : pb JDBC\n" + ex.getMessage());
        }
    }
    
    public String lireLibelleFomCodeSecteur(String code) throws DaoException{
        try {                    
            pstmtLireLibelleFomCodeSecteur.setString(1, code);
            ResultSet rs = pstmtLireLibelleFomCodeSecteur.executeQuery();
            if (rs.next())
                code = rs.getString(1);
            
            return code;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireLibelleFomCodeSecteur : pb JDBC\n" + ex.getMessage());
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
            visiteur.setCodeLabo(lireNomFromCodeLabo(rs.getString("LAB_CODE")));
            visiteur.setCodeSecteur(lireLibelleFomCodeSecteur(rs.getString("SEC_CODE")));
            
            return visiteur;
        } catch (SQLException ex) {
            throw new DaoException("DAO - chargerUnEnregistrementVisiteur : pb JDBC\n" + ex.getMessage());
        }
    }
    
    private Praticien chargerUnEnregistrementPraticien(ResultSet rs) throws DaoException {
        try {
            
            Praticien praticien = new Praticien();
            praticien.setNumero(rs.getString("PRA_NUM"));
            praticien.setNom(rs.getString("PRA_NOM"));
            praticien.setPrenom(rs.getString("PRA_PRENOM"));
            
            return praticien;
        } catch (SQLException ex) {
            throw new DaoException("DAO - chargerUnEnregistrementPraticien : pb JDBC\n" + ex.getMessage());
        }
    }
    
    public List<Rapport> lireTousLesRapports() throws DaoException{
        try {
            List<Rapport> desRapports = new ArrayList<Rapport>();
            ResultSet rs = pstmtLireTousLesRapports.executeQuery();
            while (rs.next()) {
                Rapport unRapport = chargerUnEnregistrementRapport(rs);
                desRapports.add(unRapport);
            }            
            return desRapports;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireTousLesRapports : pb JDBC\n" + ex.getMessage());
        }
    }
    
    public String lireNomFromNum(String num) throws DaoException{
        try {                    
            pstmtLireNomFromNum.setString(1, num);
            ResultSet rs = pstmtLireNomFromNum.executeQuery();
            if (rs.next())
                num = rs.getString(1);
            
            return num;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireNomFromNum : pb JDBC\n" + ex.getMessage());
        }
    }
    
    public String lireNomFromMatriculeVisiteur(String matricule) throws DaoException{
        try {                    
            pstmtLireNomFromMatriculeVisiteur.setString(1, matricule);
            ResultSet rs = pstmtLireNomFromMatriculeVisiteur.executeQuery();
            if (rs.next())
                matricule = rs.getString(1);
            
            return matricule;
        } catch (SQLException ex) {
            throw new DaoException("DAO - lireNomFromMatriculeVisiteur : pb JDBC\n" + ex.getMessage());
        }
    }
    
    private Rapport chargerUnEnregistrementRapport(ResultSet rs) throws DaoException {
        try {
            
            Rapport rapport = new Rapport();
            rapport.setNum(rs.getString("RAP_NUM"));
            rapport.setBilan(rs.getString("RAP_BILAN"));
            rapport.setDate(rs.getString("RAP_DATE"));
            rapport.setMotif(rs.getString("RAP_MOTIF"));
            rapport.setMatricule(lireNomFromNum(rs.getString("PRA_NUM")));
            rapport.setVisiteur(lireNomFromMatriculeVisiteur(rs.getString("VIS_MATRICULE")));
            
            return rapport;
        } catch (SQLException ex) {
            throw new DaoException("DAO - chargerUnEnregistrementRapport : pb JDBC\n" + ex.getMessage());
        }
    }
    
    public int ajouterUnRapport(String matricule, Integer numRap, Integer numPra, Date RapDate, String RapBilan, String RapMotif) throws DaoException {
        try {
            java.sql.Date date = new java.sql.Date(RapDate.getTime());
            pstmtAjouterUnRapport.setString(1, matricule);
            pstmtAjouterUnRapport.setInt(2, numRap);
            pstmtAjouterUnRapport.setInt(3, numPra);
            pstmtAjouterUnRapport.setDate(4, date);
            pstmtAjouterUnRapport.setString(5, RapBilan);
            pstmtAjouterUnRapport.setString(6, RapMotif);
            int nb= pstmtAjouterUnRapport.executeUpdate();
            return nb;
        } catch (SQLException ex) {
            throw new DaoException("DAO - ajouterUnRapport : pb JDBC\n" + ex.getMessage());
        }
    }  
    
    public String recupererDernierNumRap() throws DaoException{
        try {                    
            ResultSet rs = pstmtRecupererDernierNumRap.executeQuery();
            String num = null;
            if (rs.next()) {
                num = rs.getString(1);
            }
            
            return num;
        } catch (SQLException ex) {
            throw new DaoException("DAO - recupererDernierNumRap : pb JDBC\n" + ex.getMessage());
        }
    }
    
}
