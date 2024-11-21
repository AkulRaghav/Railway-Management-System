
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {
    Connection c;
    Statement s;

    public conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.c = DriverManager.getConnection("jdbc:mysql://localhost:3306/RMS?useSSL=false", "root", "root");
            this.s = this.c.createStatement();
        } catch (Exception var2) {
            System.out.println(var2);
        }

    }
}
