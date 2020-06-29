package sample;

import Connection.DbUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.BreweryDAO;
import model.CustomersDAO;

import java.io.IOException;
import java.sql.SQLException;

import static model.LoginDAO.loginExist;

public class Register_Customer_Controller {
    public Label labelMsg;
    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField password;

    @FXML
    private void signClient (ActionEvent event) throws ClassNotFoundException, SQLException{

        if(loginExist(txtLogin.getText())==true ){
            labelMsg.setText("Login already exists !!! Find another one.");
            clearTextFields();

        }
        else {

            if(checkIfFieldsAreFilled()==false) labelMsg.setText("Please fill all the fields");
            else{
                CustomersDAO.signUpCustomer(txtLogin.getText(), password.getText(), "customer");
                int id = DbUtil.getUserId(txtLogin.getText());
                CustomersDAO.fillCustomerData(id, txtFirstName.getText(), txtLastName.getText());
                labelMsg.setText("You have signed as a new customer !!!");
            }
        }
       // int tab[]=DbUtil.getAllIds();
       /* String tab[] = DbUtil.getAllLogins();
        for(int i=0;i<tab.length;i++){
            System.out.println(tab[i]);
        }*/

    }

   // switch to CustomerView
    public void login(MouseEvent mouseEvent) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene loginView = new Scene(loginParent);
        Stage window=(Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(loginView);
        window.show();

    }
    private void clearTextFields(){
        txtLogin.setText("");
        password.setText("");
    }
    private boolean checkIfFieldsAreFilled(){
        if((txtLogin.getText().length()==0) || (txtFirstName.getText().length()==0) || (txtLastName.getText().length()==0)  || (password.getText().length()==0))
            return false;
        return true;
    }
}
