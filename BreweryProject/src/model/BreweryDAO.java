package model;

import Connection.DbUtil;

import java.sql.SQLException;

public class BreweryDAO {

    public static void signUpBrewery(String login ,String password,String type) throws SQLException,ClassNotFoundException{

        String sql = "INSERT INTO users (user_login,user_password,user_type) VALUES ('"+login+"', MD5('"+password+"')  ,'"+type+"')";

        try{
            DbUtil.dbExecuteQuery(sql);
        }catch(SQLException e){
            System.out.println("Exception occur while inserting data !!!!  "+e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void fillBreweryData(int id,String name,String nip,String adress) throws SQLException,ClassNotFoundException {
        String sql = "INSERT INTO breweries (brewery_id,brewery_name, brewery_nip,brewery_addres) VALUES ('"+id+"','"+name+"','"+nip+"','"+adress+"')";

        try{
            DbUtil.dbExecuteQuery(sql);
        }catch(SQLException e){
            System.out.println("Exception occur while inserting data !!!!  "+e);
            e.printStackTrace();
            throw e;
        }

    }
    public static void deleteBrewery(int id) throws ClassNotFoundException,SQLException{
        String sql="DELETE FROM breweries  WHERE brewery_id = '"+id+"' ";
        try{
            DbUtil.dbExecuteQuery(sql);
        }catch(SQLException e){
            System.out.println("Error occured  in deleting  BeerDAO");
            e.printStackTrace();
            throw e;

        }
    }
    public static void updateBreweryName(int id,String name ) throws SQLException,ClassNotFoundException{
        String sql =" UPDATE breweries SET brewery_name ='"+ name +"' WHERE brewery_id = '"+id+"'";

        //pass update  method
        try{
            DbUtil.dbExecuteQuery(sql);

        }catch(SQLException e){
            System.out.println("Error occured  during updating");
            e.printStackTrace();
            throw e;

        }
    }
    public static void updateLogin(int id,String login ) throws SQLException,ClassNotFoundException{
        String sql =" UPDATE users SET user_login ='"+ login +"' WHERE user_id = '"+id+"'";

        //pass update  method
        try{
            DbUtil.dbExecuteQuery(sql);

        }catch(SQLException e){
            System.out.println("Error occured  during updating");
            e.printStackTrace();
            throw e;

        }
    }

}
