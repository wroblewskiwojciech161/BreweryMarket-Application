package sample;

import Connection.DbUtil;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static Connection.DbUtil.getUserId;

public class Admin_Controller implements Initializable {
    public static int id ;

    public static int breweryId = Login_Controller.userID;
    public static String breweryName =Login_Controller.breweryName;

    //  public  int breweryId;
    // przy modyfikacji jezeli brewery_id rozne od swojego to nie mozna modyfikowac


    @FXML
    public ComboBox<String> comboBoxSearch;
    ObservableList<String> comboChoice = FXCollections.observableArrayList("beer_id","beer_name","brewery_name","beer_style");

    @FXML
    public  TextField txtCustomerId;
    @FXML
    public  TextField txtMarkBeerId;
    @FXML
    public  TextField txtMarkCustomerId;

    @FXML
    public  TextField txtBeerPremiere;
    @FXML
    public  TextField txtBeerAlcohol;
    @FXML
    public  TextField txtBeerBlg;
    @FXML
    public static TextField txtBreweryName;
    @FXML
    public  TextField txtBreweryId;
    @FXML
    public  TextField txtBeerName;
    @FXML
    public  TextField txtBeerId;
    @FXML
    public  TextField txtBeerColor;
    @FXML
    public  TextField txtBeerIbu;
    @FXML
    public  TextField txtBeerStyle;
    @FXML
    public  TextField txtBeerPrice;
    @FXML
    public  static Label labelInfo;


    @FXML
    private TableView stylesTable;
    @FXML
    private TableColumn colStyle;

    @FXML
    private TableView beersTable;

    //properties for column in tableView
    @FXML
    private TableColumn colBeerId;
    @FXML
    private TableColumn colBeerName;
    @FXML
    private TableColumn colAvgMark;
    @FXML
    private TableColumn colBeerStyle;
    @FXML
    private TableColumn colBeerIbu;
    @FXML
    private TableColumn colBeerPrice;
    @FXML
    private TableColumn colBreweryId;
    @FXML
    private TableColumn colBeerColor;
    @FXML
    private TableColumn colBreweryName;
    @FXML
    private TableColumn colBeerPremiere;
    @FXML
    private TableColumn colBeerAlcohol;
    @FXML
    private TableColumn colBeerBlg;



    public static void setStartValues() {
      /*  txtBreweryName.setText(breweryName);
        txtBeerId.setText(String.valueOf(breweryId));

        txtBreweryName.setDisable(true);

        txtBeerId.setDisable(true);*/
    }
    @FXML
    private TableView marksTable;
    @FXML
    private TableColumn colMarkBeerId;
    @FXML
    private TableColumn colMarkCustomerId;
    @FXML
    private TableColumn colMark;
    @FXML
    private TableColumn colComment;


    @FXML
    public  void add(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {

        try {
            BeerDAO.addBeer(txtBeerName.getText(), txtBeerStyle.getText(), txtBeerIbu.getText(), Integer.valueOf(txtBreweryId.getText()), txtBeerColor.getText(), Double.valueOf(txtBeerPrice.getText()),
                    txtBeerPremiere.getText(),Double.valueOf(txtBeerAlcohol.getText()),Double.valueOf(txtBeerBlg.getText()));
            //BeerDAO.addBeer("piwo","ipa","30",27,"black", 8.99);
            labelInfo.setText("properly added new beer to system .");

            ObservableList<Beer> beerList = DbUtil.getBeerList();
            populateTable(beerList);

            ObservableList<Beer> styles = BeerDAO.addBeerStyles();
            populateTableStyles(styles);


            clearTextBoxes();
        }catch(SQLException e){
            System.out.println("fail during inserting new beer");
            e.printStackTrace();
            throw e;
        }
    }
    @FXML
    public void update(ActionEvent event) throws ClassNotFoundException,SQLException{

        try{
            BeerDAO.updateBeer(Integer.valueOf(txtBeerId.getText()),txtBeerName.getText(),txtBeerStyle.getText(),txtBeerIbu.getText(),txtBeerColor.getText(),Double.valueOf(txtBeerPrice.getText()),
                    txtBeerPremiere.getText(),Double.valueOf(txtBeerAlcohol.getText()),Double.valueOf(txtBeerBlg.getText()));
            labelInfo.setText("properly updated beer  with id =  "+txtBeerId.getText());

            ObservableList<Beer> beerList = DbUtil.getBeerList();
            populateTable(beerList);

            ObservableList<Beer> styles = BeerDAO.addBeerStyles();
            populateTableStyles(styles);

            clearTextBoxes();
        }catch(SQLException e){
            System.out.println("Error occured in update ( breweryview_controller)");
            e.printStackTrace();
            throw e ;

        }

    }
    @FXML
    public void delete(ActionEvent event) throws ClassNotFoundException,SQLException{

        try{
            BeerDAO.deleteBeer(Integer.valueOf(txtBeerId.getText()));
            labelInfo.setText("properly deleted beer with id = "+txtBeerId.getText());

            ObservableList<Beer> beerList = DbUtil.getBeerList();
            populateTable(beerList);

            ObservableList<Beer> styles = BeerDAO.addBeerStyles();
            populateTableStyles(styles);

            clearTextBoxes();
        }catch(SQLException e){
            System.out.println("Error occured during deleting  BreweryView_Controller");
            e.printStackTrace();
            throw  e;

        }

    }
    @FXML
    public  void deleteBrewery(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        try{
            labelInfo.setText("properly deleted brewery with id = "+txtBreweryId.getText());
            BreweryDAO.deleteBrewery(Integer.valueOf(txtBreweryId.getText()));
            labelInfo.setText("properly deleted brewery with id = "+txtBreweryId.getText());

           ObservableList<Beer> beerList = DbUtil.getBeerList();
            populateTable(beerList);

            ObservableList<Beer> styles = BeerDAO.addBeerStyles();
            populateTableStyles(styles);

            clearTextBoxes();
        }catch(SQLException e){
            System.out.println("Error occured during deleting  brewery");
            e.printStackTrace();
            throw  e;

        }
    }

    @FXML
    public  void deleteCustomer(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        try{
            labelInfo.setText("properly deleted customer with id = "+txtCustomerId.getText());
            CustomersDAO.deleteCustomer(Integer.valueOf(txtCustomerId.getText()));


            ObservableList<Beer> beerList = DbUtil.getBeerList();
            populateTable(beerList);


            ObservableList<Mark> markList = DbUtil.getMarkList();
            populateTableMark(markList);



            clearTextBoxes();
        }catch(SQLException e){
            System.out.println("Error occured during deleting  customer");
            e.printStackTrace();
            throw  e;

        }
    }
    @FXML
    public  void updateBreweryName(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        try{
            BreweryDAO.updateBreweryName(Integer.valueOf(txtBreweryId.getText()),txtBreweryName.getText());
            labelInfo.setText("properly updated brewery name . New one : "+txtBreweryName.getText());

            ObservableList<Beer> beerList = DbUtil.getBeerList();
            populateTable(beerList);

            clearTextBoxes();
        }catch(SQLException e){
            System.out.println("Error occured during deleting  BreweryView_Controller");
            e.printStackTrace();
            throw  e;

        }
    }
    @FXML
    public  void deleteMark(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        try{
            MarkDAO.deleteMark(Integer.valueOf(txtMarkBeerId.getText()),Integer.valueOf(txtMarkCustomerId.getText()));

            labelInfo.setText("properly deleted mark ");

            ObservableList<Mark> markList = DbUtil.getMarkList();
            populateTableMark(markList);

            clearTextBoxes();
        }catch(SQLException e){
            System.out.println("Error occured during deleting  BreweryView_Controller");
            e.printStackTrace();
            throw  e;

        }
    }



    private void clearTextBoxes(){
        txtBeerIbu.setText("");
        txtBeerId.setText("");
        txtBreweryName.setText("");
        txtBeerColor.setText("");
        txtBeerPrice.setText("");
        txtBeerStyle.setText("");
        txtBeerName.setText("");
        txtBeerBlg.setText("");
        txtBeerAlcohol.setText("");
        txtBeerPremiere.setText("");
    }
    private void populateTable(ObservableList<Beer> beerList) throws SQLException, ClassNotFoundException {
        beerList=BeerDAO.addBreweryNameToBeer(beerList);
        beersTable.setItems(beerList);
        System.out.println(beerList.size());
    }

    ObservableList<Person>  teamMembers = FXCollections.observableArrayList();

    public static void setBreweryIdAfterLogin(int id){
        breweryId=id;

    }
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("ZALOGOWAL SIE USER O ID " + id );
     //   int id = TemporaryValues.getInstance();
       // labelInfo.setText();

        //System.out.print("TO co ChE BARDZO "+TemporaryValues.tempUserId);
       // txtBreweryId.setText(String.valueOf(TemporaryValues.tempUserId));
        //txtBreweryId.setDisable(true);

        // przypisanie staych dla browaru
        try {
            breweryName=DbUtil.getBreweryName(breweryId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        comboBoxSearch.setItems(comboChoice);



        System.out.println("NASTAPILO LOGOWANIE JAKO BROWAR  O ID  =  "+ breweryId);
        // txtBreweryId.setDisable(true);
        // txtBreweryId.setText(String.valueOf(breweryId));


        colBeerId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, ObjectProperty<Integer>>() {
            public ObjectProperty<Integer> call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerIdProperty().asObject();
            }
        });
        colBreweryId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, ObjectProperty<Integer>>() {
            public ObjectProperty<Integer> call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBreweryIdProperty().asObject();
            }
        });
        colBeerName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, StringProperty>() {
            public StringProperty call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerNameProperty();
            }
        });
        colBreweryName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, StringProperty>() {
            public StringProperty call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBreweryNameProperty();
            }
        });
        colBeerIbu.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, StringProperty>() {
            public StringProperty call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerIbuProperty();
            }
        });
        colBeerColor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, StringProperty>() {
            public StringProperty call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerColorProperty();
            }
        });
        colBeerStyle.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, StringProperty>() {
            public StringProperty call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerStyleProperty();
            }
        });
        colBeerPrice.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, DoubleProperty>() {
            public DoubleProperty call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerPriceProperty();
            }
        });
        colBeerAlcohol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, DoubleProperty>() {
            public DoubleProperty call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerAlkoholProperty();
            }
        });
        colBeerBlg.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, DoubleProperty>() {
            public DoubleProperty call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerBlgProperty();
            }
        });
        colAvgMark.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, DoubleProperty>() {
            public DoubleProperty call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerAverageMarkProperty();
            }
        });
        colBeerPremiere.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, StringProperty>() {
            public StringProperty call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerPremiereProperty();
            }
        });


        ObservableList<Beer> beerList = null;
        try {
            beerList = DbUtil.getBeerList();
            beerList=BeerDAO.addBreweryNameToBeer(beerList);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            populateTable(beerList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        //===================================================================================+TABLE VIEW STYLES


        colStyle.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, StringProperty>() {
            public StringProperty call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerStyleProperty();
            }
        });


        ObservableList<Beer> beerStyles = null;
        try {
            beerStyles = BeerDAO.addBeerStyles();
            System.out.println("LISTA STYLUFFF :  "+beerStyles.size());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        populateTableStyles(beerStyles);

        //=================================================================================================MARKS TABLEVIEW
        //

        colMarkBeerId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mark, Integer>, ObjectProperty<Integer>>() {
            public ObjectProperty<Integer> call(TableColumn.CellDataFeatures<Mark, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerIdProperty().asObject();
            }
        });
        colMarkCustomerId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mark, Integer>, ObjectProperty<Integer>>() {
            public ObjectProperty<Integer> call(TableColumn.CellDataFeatures<Mark, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getUserIdProperty().asObject();
            }
        });
        colMark.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mark, Integer>, ObjectProperty<Integer>>() {
            public ObjectProperty<Integer> call(TableColumn.CellDataFeatures<Mark, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getMarkProperty().asObject();
            }
        });
        colComment.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mark, Integer>, StringProperty>() {
            public StringProperty call(TableColumn.CellDataFeatures<Mark, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getCommentProperty();
            }
        });

        ObservableList<Mark> markList = null;
        try {
            markList = DbUtil.getMarkList();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        populateTableMark(markList);

    }

    private void populateTableMark(ObservableList<Mark> markList) {

        marksTable.setItems(markList);

    }
    private void populateTableStyles(ObservableList<Beer> beerStyles) {


        stylesTable.setItems(beerStyles);

    }

    @FXML
    public void search(ActionEvent event)throws ClassNotFoundException,SQLException{

        if(txtBeerId.getText().contentEquals(""))labelInfo.setText("before searching the beer try to add argument in gaps :)");
        else labelInfo.setText("beer has been found");

        ObservableList <Beer> list=null;
        if(comboBoxSearch.getValue().contentEquals("brewery_name")){
            list = DbUtil.getBeerListByBreweryId(DbUtil.getBreweryIdByName(txtBreweryName.getText()));

        }else if(comboBoxSearch.getValue().contentEquals("beer_name")){
            list = DbUtil.getBeerListByName(txtBeerName.getText());

        }else if(comboBoxSearch.getValue().contentEquals("beer_style")){
            list = DbUtil.getBeerListByStyle(txtBeerStyle.getText());
        }else{
            list = BeerDAO.searchBeer(Integer.valueOf(txtBeerId.getText()));

        }
        populateTable(list);

        if(list.size()>0){
            populateTable(list);
            labelInfo.setText("search operation ");
        }
        else{
            labelInfo.setText("Record not found ");
        }

        //ObservableList <Beer> list = BeerDAO.searchBeerOfBrewery(txtBreweryName.getText());
        // populateTable(list);
    }

    @FXML
    public void searchAll(ActionEvent event)throws ClassNotFoundException,SQLException {


        ObservableList<Beer> beerList = null;
        try {
            beerList = DbUtil.getBeerList();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        populateTable(beerList);

        //myTable.getColumns().addAll(id,name,age,email);
    }

    @FXML
    public void fillById(ActionEvent event)throws ClassNotFoundException,SQLException{

        ObservableList <Beer> list = BeerDAO.searchBeer(Integer.valueOf(txtBeerId.getText()));
        // populateTable(list);
        txtBeerId.setText(String.valueOf(list.get(0).getBeerId()));
        txtBeerName.setText(list.get(0).getBeerName());
        txtBeerIbu.setText(list.get(0).getBeerIbu());
        txtBeerStyle.setText(list.get(0).getBeerStyle());
        txtBeerColor.setText(list.get(0).getBeerColor());
        txtBeerPrice.setText(String.valueOf(list.get(0).getBeerPrice()));
       // txtBreweryName.setText(DbUtil.getBreweryName(list.get(0).getBreweryId()));
        txtBeerAlcohol.setText(String.valueOf(list.get(0).getBeerAlkohol()));
        txtBeerBlg.setText(String.valueOf(list.get(0).getBeerBlg()));
        txtBeerPremiere.setText(list.get(0).getBeerPremiere());
        txtBreweryId.setText(String.valueOf(list.get(0).getBreweryId()));
      //  txtBreweryName.setText(DbUtil.getBreweryName(Integer.valueOf(txtBreweryId.getText())));
        System.out.println("NAZWA BROWARU TO   " +DbUtil.getBreweryName(Integer.valueOf(txtBreweryId.getText())));
        String tempBreweryName=DbUtil.getBreweryName(Integer.valueOf(txtBreweryId.getText()));
        txtBreweryName.setText(String.valueOf(tempBreweryName));




        //ObservableList <Beer> list = BeerDAO.searchBeerOfBrewery(txtBreweryName.getText());
        // populateTable(list);
    }
    @FXML
    public void clear(ActionEvent event) {
        clearTextBoxes();

    }

    @FXML
    public void refresh (ActionEvent event) {

        ObservableList<Mark> markList = null;
        try {
            markList = DbUtil.getMarkList();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        populateTableMark(markList);

        ObservableList<Beer> beerStyles = null;
        try {
            beerStyles = BeerDAO.addBeerStyles();
            System.out.println("LISTA STYLUFFF :  "+beerStyles.size());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        populateTableStyles(beerStyles);

        ObservableList<Beer> beerList = null;
        try {
            beerList = DbUtil.getBeerList();
            beerList=BeerDAO.addBreweryNameToBeer(beerList);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            populateTable(beerList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
