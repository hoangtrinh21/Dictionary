package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class cas implements Initializable {
    @FXML
    private TextField textfield;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("asasas");
        arr.add("asasdasdasdasd");
        arr.add("avds");
        goitu v = new goitu(textfield);
        v.getEntries().addAll(arr);
    }
}
