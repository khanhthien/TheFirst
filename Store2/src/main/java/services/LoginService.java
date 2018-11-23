package services;

import data_access_layer.UserDAO;

import java.sql.SQLException;

public class LoginService {
    UserDAO userDAO;

    public LoginService() throws SQLException, ClassNotFoundException {
        this.userDAO = new UserDAO();
    }

    public boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        return checkUsername(username) && checkPassword(password) && userDAO.findByUsernameAndPassword(username,password) != null;
    }

    private boolean checkUsername(String username){
        if(username.length() > 30  || username.equals("") ){
            return false;
        }
        return true;
    }

    private boolean checkPassword(String password){
        if(password.length() > 30 || password.equals("")){
            return false;
        }
        return true;
    }
}
