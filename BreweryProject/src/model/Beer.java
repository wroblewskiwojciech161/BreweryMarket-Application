package model;

import javafx.beans.property.*;
import javafx.css.SimpleStyleableIntegerProperty;

public class Beer {



    private IntegerProperty beerIdProperty;
    private StringProperty  nameProperty;
    private StringProperty  styleProperty;
    private StringProperty    ibuProperty;
    private DoubleProperty  priceProperty;
    private IntegerProperty breweryIdProperty;
    private StringProperty  colorProperty;
    private StringProperty  breweryNameProperty;
    private StringProperty  premiereProperty;
    private DoubleProperty  alkoholProperty;
    private DoubleProperty  blgProperty;
    private DoubleProperty  avgProperty;

   public Beer(){
        this.beerIdProperty= new SimpleIntegerProperty();
        this.nameProperty=new SimpleStringProperty();
        this.styleProperty=new SimpleStringProperty();
        this.ibuProperty= new SimpleStringProperty();
        this.priceProperty=new SimpleDoubleProperty();
        this.breweryIdProperty = new SimpleIntegerProperty();
        this.colorProperty = new SimpleStringProperty();
        this.breweryNameProperty= new SimpleStringProperty();
        this.premiereProperty= new SimpleStringProperty();
        this.alkoholProperty = new SimpleDoubleProperty();
        this.blgProperty = new SimpleDoubleProperty();
        this.avgProperty = new SimpleDoubleProperty();
    }

    //
    //-----------GETTERS && SETTERS
    //
    // ==================================================this is for  beerId
    public Integer getBeerId(){
        return beerIdProperty.get();
    }
    public void setBeerId(int id){
        this.beerIdProperty.set(id);
    }
    public IntegerProperty getBeerIdProperty(){
        return beerIdProperty;
    }
    // ================================================this is for  beer name
    public String getBeerName(){
        return nameProperty.get();
    }
    public void setBeerName(String name){
        this.nameProperty.set(name);
    }
    public StringProperty getBeerNameProperty(){
        return nameProperty;
    }
    // ==============================================this is for  beer ibu
    public String getBeerIbu(){
        return ibuProperty.get();
    }
    public void setBeerIbu(String ibu){
        this.ibuProperty.set(ibu);
    }
    public StringProperty getBeerIbuProperty(){
        return ibuProperty;
    }
    // ==============================================this is for  beer style
    public String getBeerStyle(){
        return styleProperty.get();
    }
    public void setBeerStyle(String style){
        this.styleProperty.set(style);
    }
    public StringProperty getBeerStyleProperty(){
        return styleProperty;
    }
    // ==============================================this is for  price property
    public Double getBeerPrice(){
        return priceProperty.get();
    }
    public void setBeerPrice(Double price){
        this.priceProperty.set(price);
    }
    public DoubleProperty getBeerPriceProperty(){
        return priceProperty;
    }
    // ==================================================this is for  breweryId
    public int getBreweryId(){
        return breweryIdProperty.get();
    }
    public void setBreweryId(int id){
        this.breweryIdProperty.set(id);
    }
    public IntegerProperty getBreweryIdProperty(){
        return breweryIdProperty;
    }
    // ==============================================this is for  beer color
    public String getBeerColor(){
        return colorProperty.get();
    }
    public void setBeerColor(String style){
        this.colorProperty.set(style);
    }
    public StringProperty getBeerColorProperty(){
        return colorProperty;
    }
    //=============================================================for brewery name
    public String getBreweryName(){
        return breweryNameProperty.get();
    }
    public void setBreweryName(String name){
        this.breweryNameProperty.set(name);
    }
    public StringProperty getBreweryNameProperty(){
        return breweryNameProperty;
    }
    //===============================================================for date premiere
    public String getBeerPremiere(){
        return premiereProperty.get();
    }
    public void setBeerPremiere(String style){
        this.premiereProperty.set(style);
    }
    public StringProperty getBeerPremiereProperty(){
        return premiereProperty;
    }
    //================================================================for alkohol
    public Double getBeerAlkohol(){
        return alkoholProperty.get();
    }
    public void setBeerAlkohol(Double price){
        this.alkoholProperty.set(price);
    }
    public DoubleProperty getBeerAlkoholProperty(){
        return alkoholProperty;
    }
    //=================================================================for blg
    public Double getBeerBlg(){ return blgProperty.get(); }
    public void setBeerBlg(Double price){
        this.blgProperty.set(price);
    }
    public DoubleProperty getBeerBlgProperty(){
        return blgProperty;
    }
    //=================================================================for  average
    public Double getBeerAverageMark(){ return avgProperty.get(); }
    public void setBeerAverageMark(Double price){
        this.avgProperty.set(price);
    }
    public DoubleProperty getBeerAverageMarkProperty(){
        return avgProperty;
    }

}
