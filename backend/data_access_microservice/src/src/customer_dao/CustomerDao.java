import java.sql.Connection;
import java.sql.SQLException;

public class CustomerDao {
    public static void main(String[] args) {
        ConnectionHandler connectionHandler = new ConnectionHandler();
        Connection connection = connectionHandler.openConnection();
        try {
            connectionHandler.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}