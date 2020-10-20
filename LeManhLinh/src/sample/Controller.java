package sample;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable  {
    Dictionary lWord = new Dictionary();
    DictionaryManagement mana = new DictionaryManagement();
    DictionaryCommandline dcomandline = new DictionaryCommandline();
    Dictionary history = new Dictionary();
    List<String> wordlist = new ArrayList<>();
    List<Word> wordSearchedList = new ArrayList<>();
    private int viTriLichSu = 0;
    private ObservableList<String> observableList_target;
    private ObservableList<Word> observableList_word;
    @FXML
    private TextField TargetAdd;
    @FXML
    private TextArea ExplainAdd;
    @FXML
    private TextField TuTiengAnh;
    @FXML
    private TextArea Target;
    @FXML
    private TextArea Explain;
    @FXML
    private ListView<String> recommendlist;
    
    public Controller() throws IOException {
        mana.insertFromFile(lWord);
        for (Word word : lWord.listWord) {
            wordlist.add(word.getWordTarget());
            wordSearchedList.add(word);
        }
    }
    
    public void handleSearch(ActionEvent event) throws IOException {
        String s = TuTiengAnh.getText();
        Target.setText(s);
        Word w = mana.wordlook(lWord, s);
        if (w == null) {
            Explain.setText("Khong co tu nao tim thay!");
        } else {
            Explain.setText(w.getWordExplain());
            history.listWord.add(w);
            viTriLichSu++;
        }
    }
    
    public void deleteWord(ActionEvent event) throws IOException {
        String string = TuTiengAnh.getText();
        Word w = mana.wordlook(lWord, string);
        lWord.listWord.remove(w);
        mana.dictionaryExportToFile(lWord);
        history.listWord.add(w);
        viTriLichSu++;
        lamTrangDeTimTuMoi();
    }

    public void back(ActionEvent event) {
        lamTrangDeTimTuMoi();
        if (viTriLichSu != 0) {
            Target.setText(history.listWord.get(viTriLichSu - 1).getWordTarget());
            Explain.setText(history.listWord.get(viTriLichSu - 1).getWordExplain());
            viTriLichSu -= 1;
        } else {
            Explain.setText("không có từ nào trong quá khứ");
        }
    }
    
    public void tuDangSau(ActionEvent event) {
        lamTrangDeTimTuMoi();
        if (viTriLichSu != history.listWord.size()) {
            Target.setText(history.listWord.get(viTriLichSu).getWordTarget());
            Explain.setText(history.listWord.get(viTriLichSu).getWordExplain());
            viTriLichSu += 1;
        } else {
            Explain.setText("không có từ nào trong đằng sau");
        }
    }
    
    void lamTrangDeTimTuMoi() {
        TuTiengAnh.setText("");
        Explain.setText("");
        Target.setText("");
        observableList_target.clear();
    }
    
    @FXML
    public void selectWord(MouseEvent event) throws FileNotFoundException, UnsupportedEncodingException {
        mana.dictionaryExportToFile(lWord);
        String s = recommendlist.getSelectionModel().getSelectedItem();
        Target.setText(s);
        TuTiengAnh.setText(s);
        Word w = mana.wordlook(lWord, s);
        if (w == null) {
            Explain.setText("Khong co tu nao tim thay!");
        } else {
            Explain.setText(w.getWordExplain());
            history.listWord.add(w);
            viTriLichSu++;
        }
    }
    
    @FXML
    private void typeWord() {
        String s = TuTiengAnh.getText();
        List<String> recommendWord = dcomandline.wordSearcher(lWord, s);
        List<Word> wordsearched = dcomandline.dictionarySearcher(lWord, s);
        observableList_target.clear();
        observableList_target.addAll(recommendWord);
        observableList_word.clear();
        observableList_word.addAll(wordsearched);
        if (observableList_word.size() == 0 || s == null) {
            Explain.setText("Khong co tu nao tim thay!");
            Target.setText("");
        } else {
            Target.setText(observableList_word.get(0).getWordTarget());
            Explain.setText(observableList_word.get(0).getWordExplain());
        }
    }
    
    public void speak() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager vm = VoiceManager.getInstance();
        Voice voice = vm.getVoice("kevin16");
        voice.allocate();
        voice.speak(Target.getText());
    }
    
    public void huy() {
        Target.setEditable(false);
        Explain.setEditable(false);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        observableList_target = FXCollections.observableList(wordlist);
        observableList_word = FXCollections.observableArrayList(lWord.listWord);
        recommendlist.setItems(observableList_target);
        TuTiengAnh.setOnKeyReleased(event -> typeWord());
    }

    public void HienThiCuaSoSua() throws Exception {
        TextInputDialog dialog = new TextInputDialog(Explain.getText());
        dialog.setTitle("Sửa nghĩa");
        dialog.setHeaderText("Sửa nghĩa của từ ở đây:");
        dialog.setContentText("Meaning:");
        Optional<String> result = dialog.showAndWait();
        Word tuCanSua = mana.wordlook(lWord, Target.getText());
        result.ifPresent(name -> {
            tuCanSua.setWordExplain(name);
            Explain.setText(tuCanSua.getWordExplain());
            try {
                mana.dictionaryExportToFile(lWord);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
    }
    
    public void HienThiCuaSoThem() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("them.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Thêm từ");
        primaryStage.setScene(new Scene(root));
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.showAndWait();
        lWord.listWord.clear();
        mana.insertFromFile(lWord);
        typeWord();
    }
    
        public void API() throws IOException {
        Explain.setText(API.translate("en", "vi", TuTiengAnh.getText()));
    }
}
