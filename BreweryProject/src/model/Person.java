package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

    private SimpleStringProperty id;
    private  SimpleStringProperty name;
    private SimpleStringProperty age;
    private SimpleStringProperty email;

    public Person(String id, String name, String age, String email){
        this.id=new SimpleStringProperty(id);
        this.name=new SimpleStringProperty(name);
        this.age= new SimpleStringProperty(age);
        this.email=new SimpleStringProperty(email);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAge() {
        return age.get();
    }

    public SimpleStringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}