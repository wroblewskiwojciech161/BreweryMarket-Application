package Connection;

import java.sql.*;
import com.sun.rowset.CachedRowSetImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Beer;
import model.Mark;
import sample.BreweryView_Controller;


public class DbUtil   {
    private static final String JBDC_DRIVER = "com.mysql.jdbc.Driver";
    private static Connection connection = null;
    private static final String connStr="jdbc:mysql://127.0.0.1/BeerDB";
    public static int user_id;


    public static void dbConnect() throws SQLException,ClassNotFoundException{
        try{
            Class.forName(JBDC_DRIVER);
        }catch(ClassNotFoundException e){
            System.out.println("Where is your MySql JDBC Driver");
            e.printStackTrace();
            throw e;
        }

        System.out.println("JDBC Driver has been registered !!!");

        try{
            connection =DriverManager.getConnection(connStr,"pmauser","rootroot");
        }catch(SQLException e){
            System.out.println("Connection failed! Check output console " +e);
            throw e;
        }
    }
    //method for closing the connection
    public static void dbDisconnect() throws SQLException{
        try{
            if(connection != null &&  !connection.isClosed()){
                connection.close();
            }
        }catch(Exception e) {
            throw e;
        }
    }
    //this is for insert / delete / update operation
    public static void dbExecuteQuery(String sqlStmt)throws SQLException, ClassNotFoundException{
        Statement stmt = null;
        try{
            dbConnect();
            stmt=connection.createStatement();
            stmt.executeUpdate(sqlStmt);
        }catch(SQLException e){
            System.out.println("Problem occured at dbExecuteQuery operation" + e);
            throw e;
        }
        finally{
            if(stmt!=null){
                stmt.close();
            }

            dbDisconnect();
        }
    }

    //this is for retriving data from database
    public static ResultSet dbExecute(String sqlQuery) throws ClassNotFoundException,SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;

        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery(sqlQuery);
            crs= new CachedRowSetImpl();
            crs.populate(rs);
        }catch(SQLException e){
            System.out.println("Error occured in dbExecute operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }
    //gets id int value  of user based on login (email)
    public static int getUserId(String login)throws ClassNotFoundException,SQLException{
     //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        int id = 0;
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM users WHERE user_login IN('"+login+"')");
            while(rs.next()){
                id=rs.getInt("user_id");
                System.out.println(id);
            }
           // id=rs.getInt("id");


        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getUserId) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }
       return id;
    }


    public static int[] getAllIds( )throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        int id = 0;
        int iterator=0;
        int tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM users ");
            //first iteration to get size od a table
            while(rs.next()){
                id=rs.getInt("user_id");
                iterator++;
            }

            rs=stmt.executeQuery("SELECT * FROM users ");
             tab= new int[iterator];
            iterator =0;
            // second iteration to fill table with ids
            while(rs.next()){
                id=rs.getInt("user_id");
                tab[iterator]=id;
                iterator++;
            }

        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllIds) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return tab;
    }

    public static String[] getAllLogins( )throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        String login = null;
        int iterator=0;
        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM users ");
            //first iteration to get size od a table
            while(rs.next()){
                login=rs.getString("user_login");
                iterator++;
            }

            rs=stmt.executeQuery("SELECT * FROM users ");
            tab= new String[iterator];
            iterator =0;
            // second iteration to fill table with ids
            while(rs.next()){
                login=rs.getString("user_login");
                tab[iterator]=login;
                iterator++;
            }

        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllLogs) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return tab;
    }

    public static String[] getAllPasswords( )throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        String password = null;
        int iterator=0;
        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM users ");
            //first iteration to get size od a table
            while(rs.next()){
                password=rs.getString("user_password");
                iterator++;
            }

            rs=stmt.executeQuery("SELECT * FROM users ");
            tab= new String[iterator];
            iterator =0;
            // second iteration to fill table with ids
            while(rs.next()){
                password=rs.getString("user_password");
                tab[iterator]=password;
                iterator++;
            }

        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllPassword) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return tab;
    }

    public static String getUserType(int id) throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        String type=null;

        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM users WHERE user_id IN('"+id+"')");
            while(rs.next()){
                type=rs.getString("user_type");
                System.out.println(id);
            }
            // id=rs.getInt("id");


        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getUserId) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }
        return type;
    }

    public static String getUserPassword(int id) throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        String password=null;

        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM users WHERE user_id IN('"+id+"')");
            while(rs.next()){
                password=rs.getString("user_password");
                System.out.println(id);
            }
            // id=rs.getInt("id");


        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getUserId) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }
        return password;
    }

    public static String[] getAllNips( )throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        String nip = null;
        int iterator=0;
        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM breweries ");
            //first iteration to get size od a table
            while(rs.next()){
                nip=rs.getString("brewery_nip");
                iterator++;
            }

            rs=stmt.executeQuery("SELECT * FROM breweries ");
            tab= new String[iterator];
            iterator =0;
            // second iteration to fill table with ids
            while(rs.next()){
                nip=rs.getString("brewery_nip");
                tab[iterator]=nip;
                iterator++;
            }

        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllPassword) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return tab;
    }
    public static String[] getAllNames( )throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        String name = null;
        int iterator=0;
        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM breweries ");
            //first iteration to get size od a table
            while(rs.next()){
                name=rs.getString("brewery_name");
                iterator++;
            }

            rs=stmt.executeQuery("SELECT * FROM breweries ");
            tab= new String[iterator];
            iterator =0;
            // second iteration to fill table with ids
            while(rs.next()){
                name=rs.getString("brewery_name");
                tab[iterator]=name;
                iterator++;
            }

        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllPassword) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return tab;
    }

    // zwraca liste  obiektow typu Beer przed modyfikacja bez  tam blg  lezakowanie  data partii
    public static ObservableList<Beer> getBeerList( )throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        String name = null;
        int iterator=0;
        ObservableList<Beer> beersList = FXCollections.observableArrayList();
        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM beers ");
            //first iteration to get size od a table
            while(rs.next()){
                Beer beer = new Beer();
                System.out.println("Wczytuje piwo z bazy");

                beer.setBeerId(rs.getInt("beer_id"));
                beer.setBeerName(rs.getString("beer_name"));
                beer.setBeerStyle((rs.getString("beer_style")));
                beer.setBeerIbu(rs.getString("beer_ibu"));
                beer.setBeerPrice(rs.getDouble("beer_price"));
                beer.setBreweryId(rs.getInt("brewery_id"));
                beer.setBeerColor(rs.getString("beer_color"));
                beer.setBeerBlg(rs.getDouble("beer_blg"));
                beer.setBeerAlkohol(rs.getDouble("beer_alcohol"));
                beer.setBeerPremiere(rs.getString("beer_premiere"));
                beer.setBeerAverageMark(rs.getDouble("avg_mark"));
                //after creation of each property add object to list
                beersList.add(beer);
            }



        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllPassword) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return beersList;
    }



    // zwraca liste  obiektow typu Beer przed modyfikacja bez  tam blg  lezakowanie  data partii
    public static ObservableList<Mark> getMarkList( )throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;String name = null;
        int iterator=0;
        ObservableList<Mark> list = FXCollections.observableArrayList();
        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM marks ");
            //first iteration to get size od a table
            while(rs.next()){
                Mark mark = new Mark();
               // System.out.println("Wczytuje ocene z bazy");

                mark.setBeerId(rs.getInt("beer_id"));
             //   mark.setMarkId(rs.getInt("mark_id"));
                mark.setMark(rs.getInt("mark"));
                mark.setComment(rs.getString("comment"));
                mark.setUserId(rs.getInt("customer_id"));

                //after creation of each property add object to list
                list.add(mark);
              //  System.out.println("mark Customer id " +rs.getInt("customer_id") );
            }



        }catch(SQLException e){
            System.out.println("Error occured create list of marks" + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return list;
    }

    public static ObservableList<Beer> getBeer(int id )throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String name = null;
        int iterator=0;
        ObservableList<Beer> beersList = FXCollections.observableArrayList();
        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM beers WHERE beer_id= "+id);

            //first iteration to get size od a table
            while(rs.next()){
                Beer beer = new Beer();
               // System.out.println("Wczytuje piwo z bazy  id: " +rs.getInt("beer_id"));
                beer.setBeerId(rs.getInt("beer_id"));
                beer.setBeerName(rs.getString("beer_name"));
                beer.setBeerStyle((rs.getString("beer_style")));
                beer.setBeerIbu(rs.getString("beer_ibu"));
                beer.setBeerPrice(rs.getDouble("beer_price"));
                beer.setBreweryId(rs.getInt("brewery_id"));
                beer.setBeerColor(rs.getString("beer_color"));
                beer.setBeerBlg(rs.getDouble("beer_blg"));
                beer.setBeerAlkohol(rs.getDouble("beer_alcohol"));
                beer.setBeerPremiere(rs.getString("beer_premiere"));

               // iterator++;
              //  rs2=stmt.executeQuery("SELECT * FROM breweries WHERE brewery_id = "+rs.getInt("brewery_id"));
              //  beer.setBreweryName(DbUtil.getBreweryName(rs.getInt("brewery_id")));
              //  System.out.println("nazwa browru : " + beer.getBreweryName());
               // beer.setBreweryName(rs2.getString("brewery_name"));
               // beer.setBreweryName("");
                //System.out.println("BROWAR  : "+rs2.getString("brewery_name"));
                //after creation of each property add object to list
                beersList.add(beer);
            }



        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllPassword) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return beersList;
    }

    public static Integer getBreweryID(String name) throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        int  id = 0;

        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM breweries WHERE brewery_name IN('"+name+"')");
            while(rs.next()){
                id=rs.getInt("brewery_id");
                System.out.println(id);
            }
            // id=rs.getInt("id");


        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getUserId) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }
        return id;
    }



    public static void getBreweryId(String login )throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        int id = 0;


        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM users WHERE user_login = '"+login+"'");
            //first iteration to get size od a table
            id= rs.getInt("id");
            BreweryView_Controller.breweryId=id;
           // System.out.println("BROWAR ZALOGOWANY MA ID : " + rs.getInt("user_id"));



        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllPassword) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

    }
    //dodaje  nazwe  browaru do danego pifa
    public static String getBreweryName(int id) throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
      String name = null;

        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM breweries WHERE brewery_id IN('"+id+"')");
            while(rs.next()){
                name=rs.getString("brewery_name");
              //  System.out.println(name);
             //   System.out.println("NAZWA BROWARU   PIWA O ID = "+id+ "  to  " +name);
            }
            // id=rs.getInt("id");


        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getUserId) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }
        return name;
    }

    public static int getBreweryIdByName (String name) throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        int id = 0;

        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM breweries WHERE brewery_name IN('"+name+"')");
            while(rs.next()){
                id=rs.getInt("brewery_id");


            }
            // id=rs.getInt("id");


        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getUserId) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }
        return id;
    }


    public static boolean ifMarkExist( int beer_id,int user_id)throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        int u_id = 0;
        int b_id = 0;

        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM marks ");
            //first iteration to get size od a table
            while(rs.next()){
                b_id=rs.getInt("beer_id");
                u_id=rs.getInt("customer_id");
             if((b_id == beer_id)&&(u_id==user_id))return true;
            }
            return false;


        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllPassword) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }


    }
    public static String[] getAllBeerNames()throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        String name = null;
        int iterator=0;
        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM beers ");
            //first iteration to get size od a table
            while(rs.next()){
                name=rs.getString("beer_name");
                iterator++;
            }

            rs=stmt.executeQuery("SELECT * FROM beers ");
            tab= new String[iterator];
            iterator =0;
            // second iteration to fill table with ids
            while(rs.next()){
                name=rs.getString("beer_name");
                tab[iterator]=name;
                iterator++;
            }

        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllPassword) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return tab;
    }

    public static int[] getAllBeerIds()throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        int var = 0;
        int iterator=0;
        int tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM beers ");
            //first iteration to get size od a table
            while(rs.next()){
                var=rs.getInt("beer_id");
                iterator++;
            }

            rs=stmt.executeQuery("SELECT * FROM beers ");
            tab= new int[iterator];
            iterator =0;
            // second iteration to fill table with ids
            while(rs.next()){
                var=rs.getInt("beer_id");
                tab[iterator]=var;
                iterator++;
            }

        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllPassword) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return tab;
    }
   //
    public static ObservableList<Beer> getBeerListByStyle (String style) throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        String name = null;
        int iterator=0;
        ObservableList<Beer> beersList = FXCollections.observableArrayList();
        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM beers WHERE beer_style = '"+style+"'");
            //first iteration to get size od a table
            while(rs.next()){
                Beer beer = new Beer();
                //System.out.println("Wczytuje piwo po stylu  z bazy");

                beer.setBeerId(rs.getInt("beer_id"));
                beer.setBeerName(rs.getString("beer_name"));
                beer.setBeerStyle((rs.getString("beer_style")));
                beer.setBeerIbu(rs.getString("beer_ibu"));
                beer.setBeerPrice(rs.getDouble("beer_price"));
                beer.setBreweryId(rs.getInt("brewery_id"));
                beer.setBeerColor(rs.getString("beer_color"));
                beer.setBeerBlg(rs.getDouble("beer_blg"));
                beer.setBeerAlkohol(rs.getDouble("beer_alcohol"));
                beer.setBeerPremiere(rs.getString("beer_premiere"));
                //after creation of each property add object to list
                beersList.add(beer);
            }



        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllPassword) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return beersList;
    }
    public static ObservableList<Beer> getBeerListByName (String name) throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;

        int iterator=0;
        ObservableList<Beer> beersList = FXCollections.observableArrayList();
        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM beers WHERE beer_name = '"+name+"'");
            //first iteration to get size od a table
            while(rs.next()){
                Beer beer = new Beer();
               // System.out.println("Wczytuje piwo po stylu  z bazy");

                beer.setBeerId(rs.getInt("beer_id"));
                beer.setBeerName(rs.getString("beer_name"));
                beer.setBeerStyle((rs.getString("beer_style")));
                beer.setBeerIbu(rs.getString("beer_ibu"));
                beer.setBeerPrice(rs.getDouble("beer_price"));
                beer.setBreweryId(rs.getInt("brewery_id"));
                beer.setBeerColor(rs.getString("beer_color"));
                beer.setBeerBlg(rs.getDouble("beer_blg"));
                beer.setBeerAlkohol(rs.getDouble("beer_alcohol"));
                beer.setBeerPremiere(rs.getString("beer_premiere"));
                //after creation of each property add object to list
                beersList.add(beer);
            }



        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllPassword) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return beersList;
    }
    public static ObservableList<Beer> getBeerListByBreweryId (int brewery_id) throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        String name = null;
        int iterator=0;
        ObservableList<Beer> beersList = FXCollections.observableArrayList();
        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM beers WHERE brewery_id = '"+brewery_id+"'");
            //first iteration to get size od a table
            while(rs.next()){
                Beer beer = new Beer();
               // System.out.println("Wczytuje piwo po id_browaru  z bazy");

                beer.setBeerId(rs.getInt("beer_id"));
                beer.setBeerName(rs.getString("beer_name"));
                beer.setBeerStyle((rs.getString("beer_style")));
                beer.setBeerIbu(rs.getString("beer_ibu"));
                beer.setBeerPrice(rs.getDouble("beer_price"));
                beer.setBreweryId(rs.getInt("brewery_id"));
                beer.setBeerColor(rs.getString("beer_color"));
                beer.setBeerBlg(rs.getDouble("beer_blg"));
                beer.setBeerAlkohol(rs.getDouble("beer_alcohol"));
                beer.setBeerPremiere(rs.getString("beer_premiere"));
                //after creation of each property add object to list
                beersList.add(beer);
            }



        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllPassword) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return beersList;
    }

    public static String[] getBeerStyles( )throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        String style = null;
        int id = 0;
        int iterator=0;
        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM beers ");
            //first iteration to get size od a table
            while(rs.next()){
                style=rs.getString("beer_id");
                iterator++;
            }

            rs=stmt.executeQuery("SELECT * FROM beers ");
            tab= new String[iterator];
            iterator =0;
            // second iteration to fill table with ids
            while(rs.next()){
                style=rs.getString("beer_style");
                tab[iterator]=style;
                iterator++;
            }

        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllIds) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return tab;
    }

    public static String[] userMatch(String login , String password)throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
       // Statement stmt = null;

        ResultSet rs = null;
        String tab[] = new String[2];
        //int id= 0;
        String type = null;

        try  {
            dbConnect();
            String sql = "call findUser('"+login+"','"+password+"')";
            CallableStatement stmt = connection.prepareCall(sql);
             rs = stmt.executeQuery();

            while (rs.next()) {
               // System.out.println(" ==============================ID = " + rs.getInt(1) + ", TYPE = " + rs.getString(2) );
                tab[0]= String.valueOf(rs.getInt(1));
                tab[1]=rs.getString(2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("PROCEDURE TEST  RETURN :  "+tab[0]+"   "+ tab[1]);
        user_id=Integer.valueOf(tab[0]);
        return tab;

    }


    public static ObservableList<Beer> getTopMarkedBeers () throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        String name = null;
        int iterator=0;
        ObservableList<Beer> beersList = FXCollections.observableArrayList();
        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT avg_mark,beer_name FROM beers  ORDER BY avg_mark DESC LIMIT 10");
            //first iteration to get size od a table
            while(rs.next()){
                Beer beer = new Beer();
                beer.setBeerName(rs.getString("beer_name"));
                beer.setBeerAverageMark(rs.getDouble("avg_mark"));
                System.out.println("dodaje top beer");
                //after creation of each property add object to list
                beersList.add(beer);
            }



        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllPassword) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return beersList;
    }


}
