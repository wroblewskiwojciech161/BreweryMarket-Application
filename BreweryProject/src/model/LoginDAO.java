package model;

import Connection.DbUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.SQLException;

public class LoginDAO {

   /* public void login(String login, String password) throws SQLException, ClassNotFoundException, IOException {

        if((loginExist(login)==true ) &&(passwordExist(password)==true)){
            //otworz okno po zalogowaniu
            Parent root = FXMLLoader.load(getClass().getResource("BreweryView.fxml"));
            Node node = (Node) event.
        }
        else{
            System.out.println("USER NIE ISTNIEJE!!!");
        }
    }*/

    public static boolean loginExist(String login)throws SQLException,ClassNotFoundException{
      String tab[]= DbUtil.getAllLogins();
      //if(tab.length!=0)return true;
        for(int i=0;i<tab.length;i++){
            if(tab[i].contentEquals(login))return true;
        }
       return false;
    }

    public static boolean idExist(int id)throws SQLException,ClassNotFoundException{
        int tab[]= DbUtil.getAllIds();
       // if(tab.length!=0)return true;
        for(int i=0;i<tab.length;i++){
            if(tab[i]==id)return true;
        }
        return false;
    }

    public static boolean passwordExist(String password) throws SQLException,ClassNotFoundException{
        String tab[]= DbUtil.getAllPasswords();
       // if(tab.length!=0)return true;
        for(int i=0;i<tab.length;i++){
            if(tab[i].contentEquals(password))return true;
        }
        return false;
    }

    public static boolean nipExist(String nip)throws SQLException,ClassNotFoundException{
        String tab[]= DbUtil.getAllNips();
      //  if(tab.length!=0)return true;
        for(int i=0;i<tab.length;i++){
            if(tab[i].contentEquals(nip))return true;
        }
        return false;
    }
    public static boolean nameExist(String name)throws SQLException,ClassNotFoundException{
        String tab[]= DbUtil.getAllNames();
      //  if(tab.length!=0)return true;
        for(int i=0;i<tab.length;i++){
            if(tab[i].contentEquals(name))return true;
        }
        return false;
    }

}
