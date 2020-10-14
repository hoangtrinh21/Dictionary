package sample;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.*;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class goitu {

    private final SortedSet<String> entries;
    /** context menu chua cac goi y */
    private TextField textField;
    private ContextMenu entriesPopup;
    public goitu(TextField t) {
        textField = t;

        entries = new TreeSet<>();
        entriesPopup =new ContextMenu();

        /*
            quan ly va lang nghe su thay doi cua text trong textfield
        **/

        t.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

                //neu gia tri moi == 0 thi an context menu

                if (newValue.length() == 0)
                {
                    entriesPopup.hide();
                } else
                {
                    LinkedList<String> searchResult = new LinkedList<>();

                    //search Result se nhan tat ca cac gia tri tu tu khoa can tim kiem den cuoi cua tu chua tu khoa can tim kiem

                    searchResult.addAll(entries.subSet(newValue,newValue+Character.MAX_VALUE));
                    if (entries.size() > 0)
                    {
                        populatePopup(searchResult);

                        //neu danh an thi hien len

                        if (!entriesPopup.isShowing())
                        {
                            // ham show ben dua vao object va set vi tri

                            entriesPopup.show(textField, Side.BOTTOM, 0,0);
                        }
                    } else
                    {
                        entriesPopup.hide();
                    }
                }
            }
        });

    }
    //return sortSet
    public SortedSet<String> getEntries() { return entries; }
    private void populatePopup(List<String> searchResult) {
        List<MenuItem> menuItems = new LinkedList<>();

        //toi da 10 goi y

        int maxEntries = 10;

        //tim min cua list searchresult va so hang toi da

        int count = Math.min(searchResult.size(), maxEntries);

        //0->min

        for (int i = 0; i < count; i++)
        {
            //vi search result da duoc sap xep san

            final String result = searchResult.get(i);

            Label entryLabel = new Label(result);

            //tao item suggest tu label

            CustomMenuItem item = new CustomMenuItem(entryLabel, true);

            /*
            kiem soat event
            set text = result cho object khi click vao menu item
            va an context menu
            * */

            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    textField.setText(result);
                    entriesPopup.hide();
                }
            });
            menuItems.add(item);
        }

        //xoa goi y tu lan goi y truoc

        entriesPopup.getItems().clear();

        //them tat ca goi y vao context menu

        entriesPopup.getItems().addAll(menuItems);
    }
}
