
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


public class Connect {

    private Connection connexion = null;
    private String currentBase ;
    private String nom,prenom;
    private int id;

    public Connect(String bd, String login, String mdp) {
        currentBase=bd;
        try {

            // Chargement de la classe du driver par le DriverManager
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver trouvé...");

            // Création d'une connexion sur la base de donnée
            this.connexion = DriverManager.getConnection("jdbc:mysql://PC-TP-MYSQL.insa-lyon.fr:3306/" + bd, login, mdp);
            System.out.println("Connexion établie...");

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }

    
    public void addNewUser(String nom,String Prenom){

        try {
            String sql
                    = "INSERT INTO `"+currentBase+"`.`Utilisateur` (`idUser`, `nomUser`, `prenomUser`) " +
                    "VALUES ('"+newIdUser()+"', '"+nom+"', '"+Prenom+"');";

            PreparedStatement requete = this.connexion.prepareStatement(sql);
            requete.executeUpdate();
            this.nom=nom;
            this.prenom=Prenom;

            System.out.println("Utilisateur :"+nom+" | "+Prenom+" créé et selectionné");
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.exit(-1);
        }

    }

    public int newIdUser(){

        try {
            String sql = "SELECT COUNT(*) FROM `"+currentBase+"`.`Utilisateur`;";

            PreparedStatement requete = this.connexion.prepareStatement(sql);
            ResultSet resultat = requete.executeQuery();
            resultat.next();
            return resultat.getInt(1)+1;

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.exit(-1);
            return 0; // à changer
        }

    }

    public void displayAllUsers(){
        try {
            String sql = "SELECT * FROM `"+currentBase+"`.`Utilisateur`;";

            PreparedStatement requete = this.connexion.prepareStatement(sql);
            ResultSet resultat = requete.executeQuery();

            while (resultat.next()) {
                int id = resultat.getInt(1);
                String nom = resultat.getString(2);
                String prenom = resultat.getString(3);

                System.out.println("Id numéro : #" + id + " | nom : " + nom + " | prénom  " + prenom);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.exit(-1);
        }


    }

    public void displayAllShots(){
        try {
            String sql = "SELECT * FROM `"+currentBase+"`.`Tir`;";

            PreparedStatement requete = this.connexion.prepareStatement(sql);
            ResultSet resultat = requete.executeQuery();

            while (resultat.next()) {
                int numTir = resultat.getInt(1);
                double tension = resultat.getDouble(2);
                double angle1 = resultat.getDouble(3);
                double angle2 = resultat.getDouble(4);
                double angle3 = resultat.getDouble(5);
                String zone = resultat.getString(6);

                System.out.println("tir numéro: #" + numTir + " | Tension: " + tension +
                        " | a1 =  " + angle1+ " | a2 =  "+angle2+ " | a3 =  "+angle3+" | Zone :"+zone);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.exit(-1);
        }
    }

    public int newNumTir(){
        try {
            String sql = "SELECT COUNT(numTir) FROM `"+currentBase+"`.`Tir`;";

            PreparedStatement requete = this.connexion.prepareStatement(sql);
            ResultSet resultat = requete.executeQuery();
            resultat.next();
            return resultat.getInt(1)+1;

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.exit(-1);
            return 0; // à changer
        }
    }

    public void addShot(double Tension,double a1,double a2,double a3,String zone){

        try {
            String sql
                    = "INSERT INTO `G221_B_BD1`.`Tir` (`numTir`, `tension`, `angle1`, `angle2`, `angle3`, `zone`, `idUser`)\n" +
                    "VALUES ("+newNumTir()+", "+Tension+", "+a1+", "+a2+", "+a3+", '"+zone+"', "+getId()+");";

            PreparedStatement requete = this.connexion.prepareStatement(sql);
            requete.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.exit(-1);
        }

    }

    public void setUser(String nom, String prenom){
        this.nom=nom;
        this.prenom=prenom;
    }

    public Utilisateur setUser(int ID){
        this.id=ID;
        return getUser(ID);
    }


    public int getId(){ //chercher l'id user demandé / si il n'existe pas retourne -1
        try {
            String sql = "select idUser from Utilisateur where nomUser='"+this.nom+"' AND prenomUser ='"+this.prenom+"';";

            PreparedStatement requete = this.connexion.prepareStatement(sql);
            ResultSet resultat = requete.executeQuery();
            if(resultat.next()) {
                this.id=resultat.getInt(1) ;
                return resultat.getInt(1) ;
            }else {// Pas d'id trouvé pour cet utilisateur retourne 0
                return -1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.exit(-1);
            return -1;
        }
    }


    public int getId(String Nom,String Prenom){ //chercher l'idUser en précisant l'utilisateur voulu
        try {
            String sql = "select idUser from Utilisateur where nomUser='"+Nom+"' AND prenomUser ='"+Prenom+"';";

            PreparedStatement requete = this.connexion.prepareStatement(sql);
            ResultSet resultat = requete.executeQuery();
            if(resultat.next()) {
                this.id =resultat.getInt(1) ;
                return resultat.getInt(1) ;
            }else {// Pas d'id trouvé pour cet utilisateur retourne 0
                return -1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.exit(-1);
            return -1;
        }
    }

    public LinkedList<Utilisateur> getAllUsers(){
        try {
            String sql = "SELECT * FROM `"+currentBase+"`.`Utilisateur`;";

            PreparedStatement requete = this.connexion.prepareStatement(sql);
            ResultSet resultat = requete.executeQuery();

            LinkedList<Utilisateur> listUser= new LinkedList<Utilisateur>();

            while (resultat.next()) {
                String nom = resultat.getString(2);
                String prenom = resultat.getString(3);

                listUser.add(new Utilisateur(nom,prenom));

            }
            System.out.println("Utilisateur trouvé");
            return listUser;

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.exit(-1);
            return  (new LinkedList<Utilisateur>() ); // retourne une liste vide
        }
    }

    public Utilisateur getUser(int id){

        try {

            String sql
                    = "select nomUser,prenomUser from Utilisateur where idUser="+id+";";

            PreparedStatement requete = this.connexion.prepareStatement(sql);

            ResultSet resultat = requete.executeQuery();

            if (resultat.next()) {

                String nom = resultat.getString(1);
                String prenom = resultat.getString(2);
                System.out.println("utilisateur trouvé : " + nom + " | " + prenom);
                return new Utilisateur(nom,prenom);


            }else{
                return new Utilisateur("","");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.exit(-1);
            return new Utilisateur("","");

        }
    }
}
