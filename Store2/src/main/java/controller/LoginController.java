package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.LoginService;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Text error;

    private LoginService loginService;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        loginService = new LoginService();
    }

    @FXML
    void loginAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
//        if (txtUsername.getText().equals("")) {
//            System.out.print("NULL");
//        } else
//            System.out.print(txtUsername.getText());


        if (loginService.checkLogin(txtUsername.getText(), txtPassword.getText())) {
            loadMain();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } else {
            error.setText("Đăng nhập thất bại !!!");

        }

//        loadMain();
    }

    private void loadMain() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MainFrame.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("MY APPLICATION");
        Scene scene = new Scene(root);
        stage.setScene(scene);

        MainController mainController = loader.getController();
        stage.show();
    }


}
