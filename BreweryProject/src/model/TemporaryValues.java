package model;

public class TemporaryValues {
    private static TemporaryValues instance;
    public static int id;

    public TemporaryValues(int id) {
  this.id=id;
    }

    public static TemporaryValues getInstance() {
        if (instance == null) {
            instance = new TemporaryValues( id);
        }

        return instance;
    }

}