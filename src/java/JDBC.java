
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jeremie
 */
public class JDBC {

    private ArrayList<String[]> l = new ArrayList<String[]>();

    public JDBC(ArrayList<String[]> l) {
        this.l = l;
    }

    private List<String> messages = new ArrayList<String>();

    public List<String> executerTests(HttpServletRequest request) {
        try {
            //messages.add("Chargement du driver...");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //messages.add("Driver chargé !");
        } catch (ClassNotFoundException e) {
            messages.add("Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
                    + e.getMessage());
        }
        String url = "jdbc:derby://localhost:1527/project1Users";
        String utilisateur = "root";
        String motDePasse = "root";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            //messages.add("Connexion à la base de données...");
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            //messages.add("Connexion réussie !");

            /* Création de l'objet gérant les requêtes */
            statement = connexion.createStatement();
            //messages.add("Objet requête créé !");

            // Exécution d'une requête de lecture 
            ResultSet nextId = statement.executeQuery("SELECT max(id)+1 max_id FROM users");
            int i = 1;
            if (nextId.next() && nextId.getInt("max_id") != 0) {
                i = nextId.getInt("max_id");
            }
            
            // Ajout des users
            for (int n = 0; n < l.size(); n++ ) {
                statement.executeUpdate(
                        "INSERT INTO users VALUES ("
                        + i + ",'"
                        + l.get(n)[0] + "','"
                        + l.get(n)[1] + "','"
                        + l.get(n)[2] + "')"
                );
                i++;
            }

            // Exécution d'une requête de lecture 
            resultat = statement.executeQuery("SELECT first_name, last_name, login FROM users");
            
            /* Récupération des données du résultat de la requête de lecture */
            while (resultat.next()) {
                String firstName = resultat.getString("first_name");
                String lastName = resultat.getString("last_name");
                String login = resultat.getString("login");

                /* Formatage des données pour affichage dans la JSP finale. */
                messages.add(
                        "ssn = " + firstName + ", result = " + lastName + "." + login
                );
            }
        } catch (SQLException e) {
            messages.add("Erreur lors de la connexion : <br/>"
                    + e.getMessage());
        } finally {
            //messages.add("Fermeture de l'objet ResultSet.");
            if (resultat != null) {
                try {
                    resultat.close();
                } catch (SQLException ignore) {
                }
            }

            //messages.add("Fermeture de l'objet Statement.");
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
            //messages.add("Fermeture de l'objet Connection.");
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return messages;
    }
}
