package cz.gjkt.view;

import cz.gjkt.model.Kurz;
import cz.gjkt.model.KurzyDAOJDBC;
import cz.gjkt.model.Predmet;
import cz.gjkt.model.PredmetyDAOJDBC;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import javax.swing.text.LabelView;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;;

public class KurzyController implements Initializable {
    @FXML
    TableView tableView;

    KurzyDAOJDBC kurzydao = new KurzyDAOJDBC();
    List<Kurz> kurzy;
    ObservableList<Kurz> items;
    ObservableList<Kurz> selectedItems = null;


    public void fillTable(){
        kurzy = kurzydao.getAll();
        items = FXCollections.observableList(kurzy);
        tableView.setItems(items);
    }

    public void initColumns() {
            TableColumn<String, Kurz> nazevColumn = new TableColumn<>("Název");
            nazevColumn.setCellValueFactory(new PropertyValueFactory<>("nazev"));
            TableColumn<String, Kurz> popisColumn = new TableColumn<>("Popis");
            popisColumn.setCellValueFactory(new PropertyValueFactory<>("popis"));
            TableColumn<String, Kurz> predmetColumn = new TableColumn<>("Predmet");
            predmetColumn.setCellValueFactory(new PropertyValueFactory<>("predmet"));

            tableView.getColumns().addAll(nazevColumn, popisColumn, predmetColumn);
    }
    public void handleSelection(){
            TableView.TableViewSelectionModel<Kurz> selectionModel = tableView.getSelectionModel();
            selectionModel.setSelectionMode(SelectionMode.SINGLE);

            selectedItems = selectionModel.getSelectedItems();

    }
    public void handlePridejButton(){
        Dialog<Kurz> dialog = new Dialog<>();
        dialog.setTitle("Nový Kurz");
        dialog.setWidth(400);
        dialog.setWidth(300);
        kurzDialog(dialog);

        final Optional<Kurz> vysledek = dialog.showAndWait();
        if (vysledek.isPresent()){
            Kurz novyKurz = vysledek.get();
            kurzy.add(novyKurz);
            kurzydao.insert(novyKurz);
        }
        tableView.refresh();
    }
    public void kurzDialog (Dialog dialog){

        ButtonType createButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().setAll(createButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nazevTextField = new TextField();
        Label nazevLabel = new Label("Nazev");
        TextArea popisTextArea = new TextArea();
        Label popisLabel = new Label("Popis");
        TextField zkratkaTextField = new
    }
}
