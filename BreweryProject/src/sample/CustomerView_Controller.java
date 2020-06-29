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

public class CustomerView_Controller implements Initializable {


    public static int breweryId;

    @FXML
    public ComboBox<String> comboBoxSearch;
    ObservableList<String> comboChoice = FXCollections.observableArrayList("beer_id","beer_name","brewery_name","beer_style");




    @FXML
    public static  TextField txtNameBrewery;
    @FXML
    public static  TextField txtBreweryName;
    @FXML
    public  TextField txtBreweryId;
    @FXML
    public  TextField txtBeerStyle;
    @FXML
    public  TextField txtBeerName;
    @FXML
    public  TextField txtBeerId;
    @FXML
    public  TextField txtComment;
    @FXML
    public  TextField txtMark;
    @FXML
    public Label labelInfo;



    @FXML
    private TableView beersTable;



    //properties for column in tableView
    @FXML
    private TableColumn colBeerId;
    @FXML
    private TableColumn colBeerName;
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
    @FXML
    private TableColumn colAvgMark;




    @FXML
    private TableColumn colMarkBeerId;
    @FXML
    private TableColumn colComment;
    @FXML
    private TableColumn colMark;
    @FXML
    private TableColumn colTopName;
    @FXML
    private TableColumn colTopAvgMark;
    @FXML
    private TableView marksTable;
    @FXML
    private TableView topTable;


    @FXML
    private TableView stylesTable;
    @FXML
    private TableColumn colStyle;

    public static void setStartValues() {
      /*  txtBreweryName.setText(breweryName);
        txtBeerId.setText(String.valueOf(breweryId));

        txtBreweryName.setDisable(true);

        txtBeerId.setDisable(true);*/
    }
    @FXML
    private TableView myTable;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn age;
    @FXML
    private TableColumn email;

    public static int user_id;
    @FXML
    public  void addMark(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {

       /* if ((ifCommentTextfieldsAreFilled() == true) && (ifMainTextfieldsAreFilled() == true)) {
            if (BeerDAO.ifBeerExist(txtBeerName.getText(),Integer.valueOf(txtBeerId.getText())) == true) {
                if(BeerDAO.ifMarkisProperlyDefined(txtMark.getText())==true) {

                    //sprawdzam czy podane nazwa piwa i browau  odpowiadaja podenemu id
                  //  if((BeerDAO.searchBeer(Integer.valueOf(txtBeerId.getText())).get(0).getBreweryName().contentEquals(txtBreweryName.getText()))
                     //  && (BeerDAO.searchBeer(Integer.valueOf(txtBeerId.getText())).get(0).getBeerName().contentEquals(txtBeerName.getText()))) {

                        if (CustomersDAO.ifMarkExist(Integer.valueOf(txtBeerId.getText()), user_id) == false) {
                            try {
                                BeerDAO.addBeerMark(txtMark.getText(), Integer.valueOf(txtBeerId.getText()), txtComment.getText(), user_id);
                                //BeerDAO.addBeer("piwo","ipa","30",27,"black", 8.99);
                                labelInfo.setText("properly added new beer mark to the  system !!!");

                                ObservableList<Beer> beerList = DbUtil.getBeerList();
                                populateTable(beerList);


                                clearTextBoxes();
                            } catch (SQLException e) {
                                System.out.println("ERROR : fail during adding your beer mark to the system");
                                e.printStackTrace();
                                throw e;
                            }
                        } else {
                            labelInfo.setText("This beer is marked. Try to give an opinion for a different one as well !!!");
                            txtComment.setText("This beer is already marked choose diff one");
                        }



                }else{
                    labelInfo.setText("Marks are integers from 0 to 5");
                    txtComment.setText("ERROR.Check infromation label.");
                }


            } else {
                labelInfo.setText("Beer does not exist");
                txtComment.setText("Try u mark beer that exists :)");

            }
        }
        else{
            clearTextBoxes();
            labelInfo.setText("fill all the gaps");
            txtComment.setText("fill all the gaps");

        }*/

        //if (CustomersDAO.ifMarkExist(Integer.valueOf(txtBeerId.getText()), 27) == false) {
            try {
                BeerDAO.addBeerMark(txtMark.getText(), Integer.valueOf(txtBeerId.getText()), txtComment.getText(), 27);
                //BeerDAO.addBeer("piwo","ipa","30",27,"black", 8.99);
                labelInfo.setText("properly added new beer mark to the  system !!!");

                ObservableList<Beer> beerList = DbUtil.getBeerList();
                populateTable(beerList);


                clearTextBoxes();
            } catch (SQLException e) {
                System.out.println("ERROR : fail during adding your beer mark to the system");
                e.printStackTrace();
                throw e;
            }
     /*   } else {
            labelInfo.setText("This beer is marked. Try to give an opinion for a different one as well !!!");
            txtComment.setText("This beer is already marked choose diff one");
        }*/
    }



    private void clearTextBoxes(){

        txtBeerId.setText("");
        txtBreweryName.setText("");
        txtBreweryId.setText("");
        txtBeerName.setText("");
        txtBeerStyle.setText("");

    }
    private void populateTable(ObservableList<Beer> beerList) throws SQLException, ClassNotFoundException {

        beerList=BeerDAO.addBreweryNameToBeer(beerList);
        beersTable.setItems(beerList);

        System.out.println( "Sprawdzmy czy wog dochodzi nazwa browaru   "+beerList.get(1).getBreweryName());
    }

    ObservableList<Person>  teamMembers = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id=DbUtil.user_id;
        System.out.print("Zalogowano jako user o id:  "+ user_id);



        comboBoxSearch.setItems(comboChoice);

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
        colBreweryName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, StringProperty>() {
            public StringProperty call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBreweryNameProperty();
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
        colBeerPremiere.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, StringProperty>() {
            public StringProperty call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerPremiereProperty();
            }
        });
        colAvgMark.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, DoubleProperty>() {
            public DoubleProperty call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerAverageMarkProperty();
            }
        });



        ObservableList<Beer> beerList = null;
        try {
            beerList = DbUtil.getBeerList();
            beerList=BeerDAO.addBreweryNameToBeer(beerList);
            for(int i=0 ; i< beerList.size() ; i++){
              System.out.println(beerList.get(i).getBreweryName());
            }

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
           // System.out.println("LISTA STYLUFFF :  "+beerStyles.size());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        populateTableStyles(beerStyles);
    }

    private void populateTableStyles(ObservableList<Beer> beerStyles) {


        stylesTable.setItems(beerStyles);


        //=================================================================================================MARKS TABLEVIEW
        //

        colMarkBeerId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mark, Integer>, ObjectProperty<Integer>>() {
            public ObjectProperty<Integer> call(TableColumn.CellDataFeatures<Mark, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerIdProperty().asObject();
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


        //===============================================================================TOP

        colTopAvgMark.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, DoubleProperty>() {
            public DoubleProperty call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerAverageMarkProperty();
            }
        });
        colTopName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Beer, Integer>, StringProperty>() {
            public StringProperty call(TableColumn.CellDataFeatures<Beer, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getBeerNameProperty();
            }
        });

        ObservableList<Beer> topBeers = null;
        try {
            topBeers = DbUtil.getTopMarkedBeers();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        populateTopTable(topBeers);


    }

    private void populateTopTable(ObservableList<Beer> topBeers) {
        topTable.setItems(topBeers);
    }



    private void populateTableMark(ObservableList<Mark> markList) {

        marksTable.setItems(markList);

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
/*
        if(list.size()>0){
            populateTable(list);
            labelInfo.setText("search operation ");
        }
        else{
            labelInfo.setText("Record not found ");
        }*/

        //ObservableList <Beer> list = BeerDAO.searchBeerOfBrewery(txtBreweryName.getText());
        // populateTable(list);
    }

    @FXML
    public void searchAll(ActionEvent event)throws ClassNotFoundException,SQLException {
        labelInfo.setText("All beers loaded :)");



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
        /*if(txtBeerId.getText().contentEquals(""))labelInfo.setText("before filling by id try to add id of beer :)");
        txtNameBrewery.setText("jakis tam");
        ObservableList <Beer> list = BeerDAO.searchBeer(Integer.valueOf(txtBeerId.getText()));
        // populateTable(list);
        txtBeerId.setText(String.valueOf(list.get(0).getBeerId()));
        txtBeerName.setText(list.get(0).getBeerName());
        txtBreweryId.setText(String.valueOf(list.get(0).getBreweryId()));

        txtBeerStyle.setText(list.get(0).getBeerStyle());
     //   txtBreweryName.setText(list.get(0).getBreweryName());
        //ObservableList <Beer> list = BeerDAO.searchBeerOfBrewery(txtBreweryName.getText());
        // populateTable(list);*/
        ObservableList <Beer> list = BeerDAO.searchBeer(Integer.valueOf(txtBeerId.getText()));
        // populateTable(list);
        txtBeerId.setText(String.valueOf(list.get(0).getBeerId()));
        txtBeerName.setText(list.get(0).getBeerName());

        txtBeerStyle.setText(list.get(0).getBeerStyle());

        txtBreweryName.setText(DbUtil.getBreweryName(list.get(0).getBreweryId()));


    }
    @FXML
    public void clear(ActionEvent event) {
        labelInfo.setText("gaps content has been cleared");
    clearTextBoxes();

    }
    public boolean ifMainTextfieldsAreFilled(){
        if((txtBreweryName.getText().contentEquals("")) || (txtBreweryId.getText().contentEquals(""))
                || (txtBeerId.getText().contentEquals("")) ||(txtBeerName.getText().contentEquals("")))
                {
                    return false;
                }
        return true;
    }
    public boolean ifCommentTextfieldsAreFilled(){
        if((txtComment.getText().contentEquals("")) ||(txtMark.getText().contentEquals("")))
        {
            return false;
        }
        return true;
    }
}
