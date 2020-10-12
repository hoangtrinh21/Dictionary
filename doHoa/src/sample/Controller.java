package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class Controller {
    Dictionary lWord = new Dictionary();
    DictionaryManagement mana=new DictionaryManagement();
    @FXML
    private MenuButton suaXoa;
    @FXML
    private TextField TuTiengAnh;
    @FXML
    private TextArea Target;
    @FXML
    private TextArea Explain;
    public void handleSearch(ActionEvent event) throws IOException {
        mana.insertFromFile(lWord);
        String s=TuTiengAnh.getText();
        Target.setText(s);
        Word w=mana.wordlook(lWord, s);
        if(w==null){
            Explain.setText("Khong co tu nao tim thay!");
        }
        else{
            Explain.setText(w.getWordExplain());
        }
    }
    public void deleteWord(ActionEvent event) throws IOException {
        mana.insertFromFile(lWord);
        String string=TuTiengAnh.getText();
        Word w=mana.wordlook(lWord,string);
        lWord.listWord.remove(w);
        mana.suafile(lWord);
    }
    public void choPhepSua(ActionEvent event){
        Explain.setEditable(true);
    }
    public void Sua(ActionEvent event) throws IOException {
        String target = Target.getText();
        String explain = Explain.getText();
        mana.insertFromFile(lWord);
        Word word=mana.wordlook(lWord,target);
        word.setWordExplain(explain);
        mana.suafile(lWord);
        Explain.setEditable(false);
    }
}
