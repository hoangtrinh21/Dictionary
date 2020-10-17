package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controllersua {
    Dictionary dictionarySua=new Dictionary();
    DictionaryManagement mana=new DictionaryManagement();
    @FXML
    private TextField Target;
    @FXML
    private TextField Explane;
    public void sua() throws IOException {
        mana.insertFromFile(dictionarySua);
        Word tuCanSua=mana.wordlook(dictionarySua,Target.getText());
        tuCanSua.setWordExplain(Explane.getText());
        mana.dictionaryExportToFile(dictionarySua);
    }
}
