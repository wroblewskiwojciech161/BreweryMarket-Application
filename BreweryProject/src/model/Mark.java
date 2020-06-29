package model;

import javafx.beans.property.*;

public class Mark {



    private IntegerProperty markIdProperty;
    private IntegerProperty markProperty;
    private IntegerProperty userIdProperty;
    private IntegerProperty beerIdProperty;
    private StringProperty commentProperty;


    public Mark(){
        this.beerIdProperty= new SimpleIntegerProperty();
       this.markIdProperty = new SimpleIntegerProperty();
       this.markProperty= new SimpleIntegerProperty();
       this.userIdProperty= new SimpleIntegerProperty();
       this.commentProperty = new SimpleStringProperty();
    }

    //
    //-----------GETTERS && SETTERS
    //
    // ================================================
    public Integer getMark(){
        return markProperty.get();
    }
    public void setMark(int mark){
        this.markProperty.set(mark);
    }
    public IntegerProperty getMarkProperty(){
        return markProperty;
    }
    // ================================================
    public Integer getBeerId(){
        return beerIdProperty.get();
    }
    public void setBeerId(int id){
        this.beerIdProperty.set(id);
    }
    public IntegerProperty getBeerIdProperty(){
        return beerIdProperty;
    }
    // ==============================================
    public Integer getMarkId(){
        return markIdProperty.get();
    }
    public void setMarkId(int id){
        this.markIdProperty.set(id);
    }
    public IntegerProperty getMarkIdProperty(){
        return markIdProperty;
    }
    // ===============================================
    public Integer getUserId(){
        return userIdProperty.get();
    }
    public void setUserId(int id){
        this.userIdProperty.set(id);
    }
    public IntegerProperty getUserIdProperty(){
        return userIdProperty;
    }

    // ==============================================t
    public String getComment(){
        return commentProperty.get();
    }
    public void setComment(String comment){
        this.commentProperty.set(comment);
    }
    public StringProperty getCommentProperty(){
        return commentProperty;
    }

}
