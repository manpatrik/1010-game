package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.FXMLLoader;
import hu.alkfejl.dao.EredmenyDAO;
import hu.alkfejl.dao.EredmenyDAOinpl;
import hu.alkfejl.model.Eredmeny;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private EredmenyDAO dao = new EredmenyDAOinpl();
    private static String jatekosFelhasznaloNev;
    private static int rekord;

    public static String getJatekosFelhasznaloNev() {
        return jatekosFelhasznaloNev;
    }

    public static int getRekord() {
        return rekord;
    }

    public static void setRekord(int rekord) {
        MainWindowController.rekord = rekord;
    }

    @FXML
    private TextField jatekosNev;

    @FXML
    private TableView<Eredmeny> eredmenyTable;

    @FXML
    private TableColumn<Eredmeny, String> nameColumn;

    @FXML
    private TableColumn<Eredmeny, String> scoreColumn;

    @FXML
    private TableColumn<Eredmeny, String> dateColumn;

    @FXML
    private TableColumn<Eredmeny, Integer> timeColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eredmenyTable.getItems().setAll(dao.findALl());

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        rekord = dao.findFirst();
    }

    public void start(MouseEvent mouseEvent) {
        if (jatekosNev.getText().length() > 0){
            jatekosFelhasznaloNev = jatekosNev.getText();
            App.setMainWindow(FXMLLoader.loadFXML("/fxml/jatek.fxml"));
        }else{
            Alert hibasNev = new Alert(Alert.AlertType.ERROR);
            hibasNev.setTitle("Hiba");
            hibasNev.setHeaderText("Nem adott meg nevet!");
            hibasNev.setContentText("Név nélkül nem tudjuk menteni a pontszámát.");
            hibasNev.showAndWait();
        }

    }

}
