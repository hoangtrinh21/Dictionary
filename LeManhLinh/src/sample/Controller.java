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
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable  {
    Dictionary lWord = new Dictionary();
    DictionaryManagement mana = new DictionaryManagement();
    DictionaryCommandline dcomandline = new DictionaryCommandline();
    Dictionary history = new Dictionary();
    List<String> wordlist = new ArrayList<>();
    List<Word> wordSearchedList = new ArrayList<>();
    private int viTriLichSu = 0;
    @FXML
    private TextField TuTiengAnh;

    @FXML
    private TextArea Target;

    @FXML
    private TextArea Explain;

    @FXML
    private ListView<String> recommendlist;
    private ObservableList<String> observableList_target;
    private ObservableList<Word> observableList_word;
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
    public void choPhepSua(ActionEvent event) {
        Explain.setEditable(true);
        Target.setEditable(true);
    }
    public void addWord(ActionEvent event) throws IOException {
        String target = Target.getText();
        String explain = Explain.getText();
        if(mana.dictionaryLookup(lWord, target)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("thông báo");
            alert.setHeaderText("result");
            alert.setContentText("Từ này đã có trong từ điển \n Nếu bạn muốn thêm nghĩa của từ này, bấm nút sửa");
            alert.showAndWait();
            Target.setEditable(false);
            Explain.setEditable(false);
        }
        else {
            Word word = new Word(target, explain);
            lWord.listWord.add(word);
            mana.dictionaryExportToFile(lWord);
            Target.setEditable(false);
            Explain.setEditable(false);
            history.listWord.add(word);
            viTriLichSu++;
            lamTrangDeTimTuMoi();
        }
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
    private void updateRecommendList() {

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
        Mainsua mainsua=new Mainsua();
        Stage primaryStage=new Stage();
        mainsua.start(primaryStage);
        lWord.listWord.clear();
        mana.insertFromFile(lWord);
    }
    public void HienThiCuaSoThem() throws Exception {
        MainThem mainThem=new MainThem();
        Stage primaryStage=new Stage();
        mainThem.start(primaryStage);
        lWord.listWord.clear();
        mana.insertFromFile(lWord);
    }
}