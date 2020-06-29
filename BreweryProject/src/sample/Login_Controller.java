package sample;

import Connection.DbUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.TemporaryValues;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.lang.String;
import java.util.ResourceBundle;

import static Connection.DbUtil.*;
import static model.LoginDAO.loginExist;
import static model.LoginDAO.passwordExist;

public class Login_Controller implements  Initializable {

    public static int userID;
    public static String breweryName;
    @FXML
    public Label labelMsg;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField password;


    public void login(MouseEvent event) throws IOException, SQLException, ClassNotFoundException {

        // if(method(txtLogin.getTest(),password.geteTeskt()).contetEquals("brewery"))
       if((!DbUtil.userMatch(txtLogin.getText(),password.getText())[0].isEmpty()) && (DbUtil.userMatch(txtLogin.getText(),password.getText())[1].contentEquals("brewery")) ){
       //if((loginExist(txtLogin.getText())==true ) && (getUserPassword(getUserId(txtLogin.getText())).contentEquals(password.getText()))  && (getUserType(getUserId(txtLogin.getText())).contentEquals("brewery"))){

         //  BreweryView_Controller.id=314;
            //otworz okno po zalogowaniu
            Parent breweryParent = FXMLLoader.load(getClass().getResource("Brewery_View.fxml"));
            Scene breweryView = new Scene(breweryParent);
            Stage window=(Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(breweryView);



            window.show();

          //TemporaryValues.tempUserId =getUserId(txtLogin.getText());


        }
     //  else if((loginExist(txtLogin.getText())==true ) && (getUserPassword(getUserId(txtLogin.getText())).contentEquals(password.getText()))  && (getUserType(getUserId(txtLogin.getText())).contentEquals("customer"))){
      else if((!DbUtil.userMatch(txtLogin.getText(),password.getText())[0].isEmpty()) && (DbUtil.userMatch(txtLogin.getText(),password.getText())[1].contentEquals("customer")) ){

            //otworz okno po zalogowaniu
            Parent cutomerParent = FXMLLoader.load(getClass().getResource("Customer.fxml"));
            Scene customerView = new Scene(cutomerParent);
            Stage window=(Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(customerView);
            window.show();


            Customer_Controller.user_id=getUserId(txtLogin.getText());
        }
       else if((!DbUtil.userMatch(txtLogin.getText(),password.getText())[0].isEmpty()) && (DbUtil.userMatch(txtLogin.getText(),password.getText())[1].contentEquals("admin")) ){

           Admin_Controller.id=getUserId(txtLogin.getText());
           //otworz okno po zalogowaniu jako admin
           Parent breweryParent = FXMLLoader.load(getClass().getResource("Master.fxml"));
           Scene breweryView = new Scene(breweryParent);
           Stage window=(Stage)((Node) event.getSource()).getScene().getWindow();
           window.setScene(breweryView);
           Admin_Controller.id=getUserId(txtLogin.getText());


         // TemporaryValues state = TemporaryValues.getInstance().id;
           //TemporaryValues.tempUserId=4;
       }
        else {
            System.out.println("USER NIE ISTNIEJE!!!");
            labelMsg.setText("user does not exist!Try to sign up :)");
        }

    }


    public void signUpBrewery(MouseEvent mouseEvent) throws IOException {
        Parent registerBrewery = FXMLLoader.load(getClass().getResource("Register_Brewery.fxml"));
        Scene breweryView = new Scene(registerBrewery);
        Stage window=(Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(breweryView);
        window.show();
    }



    public void signUpCustomer(MouseEvent mouseEvent) throws IOException {
        Parent registerCustomer = FXMLLoader.load(getClass().getResource("Register_Customer.fxml"));
        Scene customerView = new Scene(registerCustomer);
        Stage window=(Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(customerView);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      TemporaryValues state = new TemporaryValues(4);
    }
}
