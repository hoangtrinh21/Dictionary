package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class Controller {
    @FXML
    private MenuButton suaXoa;
    @FXML
    private TextField TuTiengAnh;
    @FXML
    private TextArea Target;
    @FXML
    private TextArea Explain;
    public void handleSearch(ActionEvent event) throws IOException {
        dictionarySearch a=new dictionarySearch();
        a.insertFromFile();
        String s=TuTiengAnh.getText();
        Target.setText(s);
        Word w=a.wordlook(s);
        if(w==null){
            Explain.setText("Khong co tu nao tim thay!");
        }
        else{
            Explain.setText(w.getWordExplain());
        }
    }
    public void deleteWord(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
        dictionarySearch a=new dictionarySearch();
        a.insertFromFile();
        String string=TuTiengAnh.getText();
        Word w=a.wordlook(string);
        a.delete(w);
        a.suafile();
    }
    public void sua(ActionEvent event){
        Explain.setEditable(true);
        String s=Explain.getText();
        String a=Target.getText();
    }
}
