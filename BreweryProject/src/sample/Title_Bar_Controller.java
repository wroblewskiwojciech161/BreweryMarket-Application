package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Title_Bar_Controller  {

    @FXML
    public void close(javafx.scene.input.MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void max(javafx.scene.input.MouseEvent mouseEvent) {

        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setFullScreenExitHint(" ");
        stage.setFullScreen(true);
        stage.setMaximized(true);
    }
    @FXML
    public void min(javafx.scene.input.MouseEvent mouseEvent) {

        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }
}
