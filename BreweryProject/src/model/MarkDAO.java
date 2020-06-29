package model;

import Connection.DbUtil;

import java.sql.SQLException;

public class MarkDAO {

    public static void deleteMark(int beer_id,int customer_id ) throws ClassNotFoundException, SQLException {
        String sql="DELETE FROM marks WHERE beer_id = '"+beer_id+"' AND customer_id = '"+customer_id+"' ";
        try{
            DbUtil.dbExecuteQuery(sql);
        }catch(SQLException e){
            System.out.println("Error occured  in deleting  MarkDao");
            e.printStackTrace();
            throw e;

        }
    }
}
