package sample;

import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ControllerThem {
    Dictionary dictionaryThem=new Dictionary();
    DictionaryManagement mana=new DictionaryManagement();
    @FXML
    private TextField Target;
    @FXML
    private TextField Explain;
    public void them() throws IOException {
        mana.insertFromFile(dictionaryThem);
        Word tuCanThem=new Word(Target.getText(),Explain.getText());
        Word Sosanh =mana.wordlook(dictionaryThem,tuCanThem.getWordTarget());
        if(Sosanh==null ||Sosanh.getWordTarget().equals(tuCanThem.getWordExplain())){
            dictionaryThem.listWord.add(tuCanThem);
            mana.dictionaryExportToFile(dictionaryThem);
        }

    }
}
