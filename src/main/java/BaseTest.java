import com.mysql.cj.xdevapi.SessionFactory;

import java.lang.module.Configuration;

public class BaseTest
{
    // Creacion del elemento de la fabrica para conectarse con la base de datos.
    private static SessionFactory factory;

    public static void main(String[] args)
    {
        try
        {
            // factory = new Configuration().configure().buildSessionFactory();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
