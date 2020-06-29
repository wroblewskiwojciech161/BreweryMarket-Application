package model;

import Connection.DbUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class BeerDAO {


    public static void addBeerMark(String mark ,int beer_id,String comment,int user_id) throws SQLException,ClassNotFoundException{

        String sql = "INSERT INTO marks" +
                " (beer_id,customer_id,mark,comment) VALUES" +
                " ('"+beer_id+"','"+user_id+"','"+mark+"','"+comment+"')";



        try{
            DbUtil.dbExecuteQuery(sql);
        }catch(SQLException e){
            System.out.println("Exception occur while inserting data !!!!  "+e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void addBeer(String name ,String style,String ibu,int breweryId,String color,double  price,String premiere,Double alcohol,Double blg) throws SQLException,ClassNotFoundException{

        String sql = "INSERT INTO beers" +
                " (beer_name,brewery_id,beer_style,beer_ibu,beer_price,beer_color,beer_premiere,beer_alcohol,beer_blg) VALUES" +
                " ('"+name+"','"+breweryId+"','"+style+"','"+ibu+"','"+price+"','"+color+"','"+premiere+"','"+alcohol+"','"+blg+"')";

        try{
            DbUtil.dbExecuteQuery(sql);
        }catch(SQLException e){
            System.out.println("Exception occur while inserting data !!!!  "+e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void updateBeer(int beerId,String name ,String style,String ibu,String color,double  price,String premiere,Double alcohol,Double blg) throws SQLException,ClassNotFoundException{
        String sql ="UPDATE beers SET beer_name ='"+ name+"', beer_style='"+style+"', beer_ibu='"+ibu+"', beer_price='"+price+"', beer_color='"+color+"', beer_premiere = '"+premiere+"', beer_blg = '"+blg+"', beer_alcohol = '"+alcohol+"'  WHERE beer_id='"+beerId+"'";

        //pass update  method
        try{
            DbUtil.dbExecuteQuery(sql);

        }catch(SQLException e){
            System.out.println("Error occured  during updating");
            e.printStackTrace();
            throw e;

        }
    }

    public static void deleteBeer(int id) throws ClassNotFoundException,SQLException{
        String sql="DELETE FROM beers WHERE beer_id='"+id+"' ";
        try{
            DbUtil.dbExecuteQuery(sql);
        }catch(SQLException e){
            System.out.println("Error occured  in deleting  BeerDAO");
            e.printStackTrace();
            throw e;

        }
    }

    public static ObservableList<Beer> searchBeer (int id) throws SQLException,ClassNotFoundException{

        try{
           // ResultSet rsSet= DbUtil.dbExecute(sql);
            ObservableList<Beer> list = DbUtil.getBeer(id);
            System.out.println("search bear zwraca liste o dlugosci " + list.size());
            return list;
        }catch(SQLException e){
            System.out.println("Error occured while searching data");
            e.printStackTrace();
            throw e;
        }

    }

    public static ObservableList<Beer> searchBeerOfBrewery (String name) throws SQLException,ClassNotFoundException{

        try{
            // ResultSet rsSet= DbUtil.dbExecute(sql);
            System.out.print(DbUtil.getBreweryID(name));
            ObservableList<Beer> list = DbUtil.getBeer(27);
            System.out.print("liczba piw  browaru  "+list.size());
            return list;
        }catch(SQLException e){
            System.out.println("Error occured while searching data");
            e.printStackTrace();
            throw e;
        }

    }
    public static ObservableList<Beer> addBreweryNameToBeer(ObservableList<Beer> list) throws SQLException, ClassNotFoundException {

        for(int i=0;i<list.size();i++){
            list.get(i).setBreweryName(DbUtil.getBreweryName(list.get(i).getBreweryId()));
        }

        return list;
    }

    public static ObservableList<Beer> addBeerStyles () throws SQLException, ClassNotFoundException {

        ObservableList<Beer> list = FXCollections.observableArrayList();

        //Array with duplicate elements
       // Integer[] numbers = new Integer[] {1,2,3,4,5,1,3,5};
        String tab[] = DbUtil.getBeerStyles();

        //Create set from array elements
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>( Arrays.asList(tab) );

        //Get back the array without duplicates
        String[] tabWithoutDuplicates = linkedHashSet.toArray(new String[] {});
      //  System.out.println("tablica bez potworzen  ========== size ========== "  + tabWithoutDuplicates.length);

        for(int i=0;i<tabWithoutDuplicates.length;i++){
            Beer beer = new Beer();
            beer.setBeerStyle(tabWithoutDuplicates[i]);
            list.add(beer);
        }


        return list;
    }

    public static boolean ifBeerExist(String beer_name,int beer_id) throws SQLException, ClassNotFoundException {

        String[] names = DbUtil.getAllBeerNames();
        int[]  ids = DbUtil.getAllBeerIds();
        boolean result1 = false;
        boolean result2 = false;

        for(int i=0;i<names.length;i++){
         if(beer_name.contentEquals(names[i]))  result1= true;
        }
        for(int i=0;i<ids.length;i++){
            if(beer_id == ids[i])  result2 = true;
        }


    if((result1 == true) &&(result2 == true))return true;
       else  return false;

    }

    public static boolean ifMarkisProperlyDefined(double mark){

        if((mark==0)||(mark==1)||(mark==2)||(mark==3)||(mark==4)||(mark==5)) return true;
        else return false;
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }


}
