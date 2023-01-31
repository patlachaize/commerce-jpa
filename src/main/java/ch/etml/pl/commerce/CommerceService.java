package ch.etml.pl.commerce;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class CommerceService {

    private Connection connection;

    public CommerceService() throws IOException, SQLException {
        Properties prop = new Properties();
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("commerce.properties");
        prop.load(input);

        connection = DriverManager.getConnection(
                            prop.getProperty("url"),
                            prop.getProperty("user"),
                            prop.getProperty("password")
                    );

    }
}
