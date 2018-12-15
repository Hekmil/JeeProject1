
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

    private ArrayList<String[]> l = new ArrayList<>();

    public JDBC(ArrayList<String[]> l) {
        this.l = l;
    }

    private ArrayList<User> messages = new ArrayList<>();

    public ArrayList<User> executerTests(HttpServletRequest request) {
        try {
            //messages.add("Chargement du driver...");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //messages.add("Driver chargé !");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
                    + e.getMessage());
        }
        String url = "jdbc:derby://localhost:1527/project1";
        String utilisateur = "root";
        String motDePasse = "root";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

            /* Création de l'objet gérant les requêtes */
            statement = connexion.createStatement();

            // Exécution d'une requête de lecture 
            ResultSet nextId = statement.executeQuery("SELECT max(id)+1 max_id FROM users");
            int i = 1;
            if (nextId.next() && nextId.getInt("max_id") != 0) {
                i = nextId.getInt("max_id");
            }

            // Ajout des users
            for (int n = 0; n < l.size(); n++) {
                statement.executeUpdate(
                        "INSERT INTO users VALUES ('"
                        + i + "','"
                        + l.get(n)[0] + "','"
                        + l.get(n)[1] + "','"
                        + l.get(n)[2] + "')"
                );
                i++;
            }

            resultat = statement.executeQuery("SELECT id, first_name, last_name, login FROM users");

            while (resultat.next()) {
                User u = new User();
                u.setId(resultat.getString("id"));
                u.setFirst_name(resultat.getString("first_name"));
                u.setLast_name(resultat.getString("last_name"));
                u.setLogin(resultat.getString("login"));
                messages.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion : <br/>"
                    + e.getMessage());
        } finally {
            if (resultat != null) {
                try {
                    resultat.close();
                } catch (SQLException ignore) {
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
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
