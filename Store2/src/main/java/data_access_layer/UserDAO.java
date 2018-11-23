package data_access_layer;

import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    Connection connection;

    public UserDAO() throws SQLException, ClassNotFoundException {
        this.connection = ConnectionUlti.getConnection();
    }

    public List<User> findByUsernameAndPassword(String username , String password)throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND passwords = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();
        List<User> users = new ArrayList<User>();
        while (rs.next()){
            users.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),
                    rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
        }
        return users;
    }
}
