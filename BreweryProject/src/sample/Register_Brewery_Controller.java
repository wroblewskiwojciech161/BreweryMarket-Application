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

import static Connection.DbUtil.*;
import static Connection.DbUtil.getUserId;
import static model.LoginDAO.*;

public class Register_Brewery_Controller {
    public Label labelMsg;
    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtAdress;
    @FXML
    private TextField txtNip;
    @FXML
    private TextField txtName;
    @FXML
    private PasswordField password;

    @FXML
    private void signBrewery (ActionEvent event) throws ClassNotFoundException, SQLException{

        if(loginExist(txtLogin.getText())==true ){
            labelMsg.setText("Login already exists !!! Find another one.");
            clearTextFields();

        }
        else if(nipExist(txtNip.getText())==true ){
            labelMsg.setText("Nip already exists !!! Find another one.");
            clearTextFields();

        }
        else if(nameExist(txtName.getText())==true ){
            labelMsg.setText("Name already exists !!! Find another one.");
            clearTextFields();

        }
        else{

            if(checkIfFieldsAreFilled()==false)labelMsg.setText("Please fill all fields .");
            else{
                BreweryDAO.signUpBrewery(txtLogin.getText(), password.getText(), "brewery");
                int id = DbUtil.getUserId(txtLogin.getText());
                BreweryDAO.fillBreweryData(id, txtName.getText(), txtNip.getText(), txtAdress.getText());

                labelMsg.setText("You have signed as a new brewery. Welcome in our team !!!");

                //przekazuje dane jako stale do ekranu po zalogowaniu
                //w celu okreslenia danego id nazwy by zmniejszyc mozliwosci na modyfikacje ze strony browaru

               // BreweryView_Controller.breweryName=txtName.getText();
                BreweryView_Controller.breweryId=id;



                clearTextFields();
            }
        }



    }


    public void login(MouseEvent mouseEvent) throws IOException {
        Parent parentLogin = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene loginView = new Scene(parentLogin);
        Stage window=(Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(loginView);



        window.show();
    }
    public void clearTextFields(){
        txtLogin.setText("");
        txtAdress.setText("");
        txtNip.setText("");
        txtName.setText("");
        password.setText("");

    }
    private boolean checkIfFieldsAreFilled(){
        if((txtLogin.getText().length()==0) || (txtName.getText().length()==0) || (txtNip.getText().length()==0)  || (password.getText().length()==0) || (txtAdress.getText().length()==0))
            return false;
        return true;
    }
}
