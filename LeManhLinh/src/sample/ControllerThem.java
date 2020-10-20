package sample;

import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ControllerThem {
    Dictionary dictionaryThem=new Dictionary();
    DictionaryManagement mana=new DictionaryManagement();
    @FXML
    private TextField TargetAdd;
    @FXML
    private TextArea ExplainAdd;

    public void them() throws IOException {
        mana.insertFromFile(dictionaryThem);
        Word tuCanThem = new Word(TargetAdd.getText(), ExplainAdd.getText());
        if(!mana.dictionaryLookup(dictionaryThem, TargetAdd.getText())) {
            dictionaryThem.listWord.add(tuCanThem);
            mana.dictionaryExportToFile(dictionaryThem);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thành công");
            alert.setHeaderText("Đã thêm từ này vào từ điển");
//            alert.setContentText("Từ này đã có trong từ điển \n Nếu bạn muốn thêm nghĩa của từ này, bấm nút sửa");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Từ này đã có trong từ điển \n Nếu bạn muốn thêm nghĩa của từ này, bấm nút sửa");
//            alert.setContentText("Từ này đã có trong từ điển \n Nếu bạn muốn thêm nghĩa của từ này, bấm nút sửa");
            alert.showAndWait();

        }
    }
}
