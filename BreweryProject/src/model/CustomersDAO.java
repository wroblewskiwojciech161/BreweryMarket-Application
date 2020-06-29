package model;

import Connection.DbUtil;

import java.sql.SQLException;

public class CustomersDAO {

    public static void signUpCustomer(String login ,String password,String type) throws SQLException,ClassNotFoundException{

        String sql = "INSERT INTO users (user_login,user_password,user_type) VALUES ('"+login+"',MD5('"+password+"'),'"+type+"')";

        try{
            DbUtil.dbExecuteQuery(sql);
        }catch(SQLException e){
            System.out.println("Exception occur while inserting data !!!!  "+e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void fillCustomerData(int id,String firstName,String lastName) throws SQLException,ClassNotFoundException {
        String sql = "INSERT INTO customers (customer_id,customer_firstname, customer_lastname) VALUES ('"+id+"','"+firstName+"','"+lastName+"')";

        try{
            DbUtil.dbExecuteQuery(sql);
        }catch(SQLException e){
            System.out.println("Exception occur while inserting data !!!!  "+e);
            e.printStackTrace();
            throw e;
        }

    }
    public static boolean ifMarkExist(int beer_id,int user_id)  throws SQLException,ClassNotFoundException {
       if(DbUtil.ifMarkExist(beer_id,user_id))return true;
       else return false;
    }

    public static void deleteCustomer(int id) throws ClassNotFoundException,SQLException{
        String sql="DELETE FROM customers  WHERE customer_id = '"+id+"' ";
        try{
            DbUtil.dbExecuteQuery(sql);
        }catch(SQLException e){
            System.out.println("Error occured  in deleting  BeerDAO");
            e.printStackTrace();
            throw e;

        }
    }
}
