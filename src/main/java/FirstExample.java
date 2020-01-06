//STEP 1. Import required packages
import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class FirstExample {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    // Conexion con la base de datos, al final tiene que ir el nombre de la misma.
    static final String DB_URL = "jdbc:mysql://localhost/Oscars";

    //  Credenciales de la persona que quiere entrar a la base de datos
    static final String USER = "David Ortegon";
    static final String PASS = "exito555";

    public static void main(String[] args) {
        // Declaracion de la conexion con la que despues se hacen las solicitudes a la base de datos.
        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT actorAge FROM Oscars.winners";
            String sql2 = "SELECT avg(actorAge) FROM Oscars.winners";
            String sql3 = "SELECT std(actorAge) FROM Oscars.winners";
            String sql4 = "select count(award_year) from Oscars.winners where nameAndLastName like '%Fonda'";
            String sql5 = "select min(actorAge) from Oscars.winners where gender = 'M';";
            String sql6 = "select min(actorAge) from Oscars.winners where gender = 'F';";
            String sql7 = "select (count(gender)/177)*100 from Oscars.winners where gender = 'F';";
            String sql8 = "select c.nameAndLastName, count(1) as total from Oscars.winners c \n" +
                    "group by c.nameAndLastName having count(1) > 1\n" +
                    "order by total desc; ";

            String sql9 = "select c.award_year, count(1) as total from Oscars.winners c \n" +
                    "group by c.award_year having count(1) > 1\n" +
                    "order by award_year asc; \n";

            String sql10 = "select award_year, movie, nameAndLastName from Oscars.winners\n" +
                    "where (nameAndLastName like \"Al Pacino\"  or nameAndLastName like \"Tom Hanks\" or nameAndLastName like \"Colin Firth\"); \n";

            String sql11 = "select c.gender, count(1) as total from Oscars.winners c \n" +
                    "group by c.gender having count(1) > 1; ";


            // donde se ponen los RESULTADOS De la solicitud que se esta haciendo.
            ResultSet rs = stmt.executeQuery(sql11);

            //STEP 5: Extract data from result set
            while (rs.next())
            {
                //Retrieve by column name
                // String añosGanadores = rs.getString("actorAge");
                // int promedioResultado  = rs.getInt(1);
                // int desvesta = rs.getInt(1);
                // int counterForda = rs.getInt(1);
                // int maxEdad = rs.getInt(1);
                // int procentW = rs.getInt(1);
                // String actorName = rs.getString("nameAndLastName");
                // String numberPrices = rs.getString("total");
                // String yearAward = rs.getString("award_year");
                // String numberPrices = rs.getString("total");
                // String years = rs.getString("award_year");
                // String movies = rs.getString("movie");
                // String names = rs.getString("nameAndLastName");
                String genders = rs.getString("gender");
                String numberPrices = rs.getString("total");


                //Display values
                // System.out.println("The years of the winners are: " + añosGanadores + '\n');
                // System.out.print("The average of the age of the actors is: " + promedioResultado + '\n');
                // System.out.println("The std of the actor`s age is: " + desvesta);
                // System.out.println("The number of prices for the Forda family are: " + counterForda);
                // System.out.println("The min age for the women is: " + maxEdad);
                // System.out.println("The percentage of women winners is: " + procentW);
                // System.out.println(actorName + " " + numberPrices + '\n');
                // System.out.println(yearAward + " " + numberPrices + '\n');
                // System.out.println(years + " " + movies + " " + names + " " + '\n');
                System.out.println(genders + " " + numberPrices + '\n');

            }

            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources, cerramiento de la conexion que se hizo con la base de datos.
            try {
                if (stmt != null)
                {
                    stmt.close();
                }
            }

            catch (SQLException se2)
            {

            }// nothing we can do
            try {
                if (conn != null)
                {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println('\n' + "Goodbye!");
    }
}