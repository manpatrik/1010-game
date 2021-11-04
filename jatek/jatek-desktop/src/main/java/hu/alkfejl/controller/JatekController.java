package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.FXMLLoader;
import hu.alkfejl.Jatek;
import hu.alkfejl.dao.EredmenyDAO;
import hu.alkfejl.dao.EredmenyDAOinpl;
import hu.alkfejl.model.Eredmeny;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class JatekController implements Initializable {

    private boolean torlesSegitseg = false;
    private boolean sorTorles;
    private static Jatek jatek;
    private Thread pluszPontThread;
    private Timer timer;
    private final EredmenyDAO dao = new EredmenyDAOinpl();
    private Alert nincsElégPont = new Alert(Alert.AlertType.WARNING);
    private Alert nincsTobbSegitseg = new Alert(Alert.AlertType.WARNING);


    @FXML
    public ImageView kep1;

    @FXML
    public ImageView kep2;

    @FXML
    public ImageView kep3;

    @FXML
    public Label pluszPont;

    @FXML
    public Label pontszam;

    @FXML
    public GridPane jatekTabla;

    @FXML
    public Label rekord;

    @FXML
    public Button ujElemekGomb;

    @FXML
    public Button oVSTorleseGomb;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jatek = new Jatek();
        jatek.setIdoKezdes(System.currentTimeMillis());
        rekord.setText(Integer.toString(MainWindowController.getRekord()));
        kep1.setImage(new Image("/kepek/"+ jatek.getLathatoElemek()[0]+".png" ));
        kep2.setImage(new Image("/kepek/"+ jatek.getLathatoElemek()[1]+".png" ));
        kep3.setImage(new Image("/kepek/"+ jatek.getLathatoElemek()[2]+".png" ));
        nincsElégPont.setTitle("Hiba");
        nincsElégPont.setHeaderText("Nincs elég pontod!");
        nincsElégPont.setContentText("A segítségek felhasználásához 50 pont kell!");
        nincsTobbSegitseg.setTitle("Hiba");
        nincsTobbSegitseg.setHeaderText("Nincs több segítség!");
        nincsTobbSegitseg.setContentText("Összesen 3 segítsége volt ami már elfogyott!");
    }

    private void dragged(ImageView kep1, int i) {
        kep1.setScaleX(1.95);
        kep1.setScaleY(1.95);
        kep1.setTranslateX(MouseInfo.getPointerInfo().getLocation().getX() - App.getStage().getX() - 507);
        kep1.setTranslateY(MouseInfo.getPointerInfo().getLocation().getY() - App.getStage().getY() - i);
    }

    public void dragged1(MouseEvent mouseEvent) {
        dragged(kep1, 122);
    }

    public void dragged2(MouseEvent mouseEvent) {
        dragged(kep2, 227);
    }

    public void dragged3(MouseEvent mouseEvent) {
        dragged(kep3, 332);
    }

    private void dragOver(ImageView kep, int index){
        int x = (int) (MouseInfo.getPointerInfo().getLocation().getX() - App.getStage().getX()) - 7;
        int y = (int) (MouseInfo.getPointerInfo().getLocation().getY() - App.getStage().getY()) - 70;
        int oszlop = (int) x / 40 + 1;
        int sor = (int) y / 40 + 1;
        int pont = 0;
        if(x > 0 && x < 400 && y > 0 && y < 400){
            if ((pont = jatek.setCell(sor - 1, oszlop - 1, jatek.getLathatoElemek()[index])) > 0) {
                megjelenit();
                timer = new Timer(500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        megjelenit();
                        timer.stop();
                    }
                });
                timer.start();
                int pPont = jatek.beteltE(pont);
                pontszam.setText(Integer.toString(jatek.getPontszam()));
                startTaskOnThread(() -> pluszPontTask(pPont));

            }
        }
        kep.setTranslateX(0);
        kep.setTranslateY(0);
        kep.setScaleX(1);
        kep.setScaleY(1);
        if(MainWindowController.getRekord() < jatek.getPontszam()){
            rekord.setText(Integer.toString(jatek.getPontszam()));
        } else {
            rekord.setText(Integer.toString(MainWindowController.getRekord()));
        }
        if(pont != 0){
            kep.setVisible(false);
            jatek.setLathatoElemNull(index);
            if (jatek.getLathatoElemek()[0] == null && jatek.getLathatoElemek()[1] == null && jatek.getLathatoElemek()[2] == null){
                jatek.ujElemek();
                elemekMegjelenit();
            }
            if (jatek.vesztettE()){
                vesztett();
            }
        }
    }

    private void elemekMegjelenit() {
        kep1.setImage(new Image("/kepek/"+ jatek.getLathatoElemek()[0]+".png" ));
        kep2.setImage(new Image("/kepek/"+ jatek.getLathatoElemek()[1]+".png" ));
        kep3.setImage(new Image("/kepek/"+ jatek.getLathatoElemek()[2]+".png" ));
        kep1.setVisible(true);
        kep2.setVisible(true);
        kep3.setVisible(true);
    }

    private String idoAtalakit(long idoKezdes, long idoVege) {
        long ido = (idoVege - idoKezdes) / 1000;
        int ora = (int) ido / 3600;
        ido %= 3600;
        int perc = (int) ido / 60;
        int masodPerc = (int) ido % 60;
        return ora + ":" + perc + ":" + masodPerc;
    }

    public void dragOver1(MouseEvent mouseEvent) {
        dragOver(kep1, 0);
    }

    public void dragOver2(MouseEvent mouseEvent) {
        dragOver(kep2, 1);
    }

    public void dragOver3(MouseEvent mouseEvent) {
        dragOver(kep3, 2);
    }

    private void startTaskOnThread(Runnable task){
        if(pluszPontThread != null){
            pluszPontThread.stop();
        }
        pluszPontThread = new Thread(task);
        pluszPontThread.setDaemon(true);
        pluszPontThread.start();
    }

    private void pluszPontTask(int pont){
        try {
            Platform.runLater(() -> pluszPont.setText("+"+Integer.toString(pont)));
            Thread.sleep(5000);
            Platform.runLater(() -> pluszPont.setText(""));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pluszPontThread = null;
    }

    private void megjelenit(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (jatek.getTable()[i][j]){
                    ((Pane)jatekTabla.getChildren().get(i * 10 + j)).setStyle("-fx-background-color: grey;");
                } else {
                    ((Pane)jatekTabla.getChildren().get(i * 10 + j)).setStyle("-fx-background-color: none;");
                }
            }
        }
    }

    public void ujElemekKerese(MouseEvent mouseEvent) {
        if (jatek.getPontszam() >= 50 && jatek.getUjElemSegitseg() != 0){
            ButtonType buttonTypeNo = new ButtonType("Nem", ButtonBar.ButtonData.NO);
            ButtonType buttonTypeYes = new ButtonType("Igen", ButtonBar.ButtonData.YES);
            Alert ujElemekMegerosites = new Alert(Alert.AlertType.CONFIRMATION, null, buttonTypeYes, buttonTypeNo);
            ujElemekMegerosites.setTitle("Segítség");
            ujElemekMegerosites.setHeaderText("Biztos új elhelyezhető elemeket szeretne?");
            ujElemekMegerosites.setContentText("Ez 50 pontjába fog kerülni!");
            Optional<ButtonType> valasz = ujElemekMegerosites.showAndWait();
            if (valasz.get() == buttonTypeYes){
                jatek.segitseg();
                jatek.ujElemek();
                jatek.setUjElemSegitseg();
                pontszam.setText(Integer.toString(jatek.getPontszam()));
                ujElemekGomb.setText("Új elemek kérése "+ jatek.getUjElemSegitseg() +"x");
                elemekMegjelenit();
                if (jatek.vesztettE()){
                    vesztett();
                }
            }
        }else{
            if (jatek.getUjElemSegitseg() == 0){
                nincsTobbSegitseg.show();
            }else {
                nincsElégPont.show();
            }
        }
        if(MainWindowController.getRekord() < jatek.getPontszam()){
            rekord.setText(Integer.toString(jatek.getPontszam()));
        } else {
            rekord.setText(Integer.toString(MainWindowController.getRekord()));
        }
    }

    public void oszlopVagySorTorlese(MouseEvent mouseEvent) {
        if (jatek.getPontszam() >= 50 && jatek.getTorlesSegitseg() != 0){
            ButtonType buttonTypeOszlop = new ButtonType("Oszlop", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeSor = new ButtonType("Sor", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("mégse", ButtonBar.ButtonData.NO);
            Alert ujElemekMegerosites = new Alert(Alert.AlertType.CONFIRMATION, null, buttonTypeSor, buttonTypeOszlop, buttonTypeNo);
            ujElemekMegerosites.setTitle("Segítség");
            ujElemekMegerosites.setHeaderText("Mit szeretne törölni?");
            ujElemekMegerosites.setContentText("Ez 50 pontjába fog kerülni!");
            Optional<ButtonType> valasz = ujElemekMegerosites.showAndWait();
            Alert torlesInfo = new Alert(Alert.AlertType.INFORMATION);
            torlesInfo.setTitle("Segítség");
            if (valasz.get() == buttonTypeSor){
                torlesInfo.setHeaderText("Kattintson a törlendő sorba!");
                torlesInfo.showAndWait();
                jatek.segitseg();
                jatek.setTorlesSegitseg();
                pontszam.setText(Integer.toString(jatek.getPontszam()));
                oVSTorleseGomb.setText("Oszlop vagy sor törlés "+ jatek.getTorlesSegitseg() +"x");
                sorTorles = true;
                torlesSegitseg = true;
                kep1.setDisable(true);
                kep2.setDisable(true);
                kep3.setDisable(true);
            }else if(valasz.get() == buttonTypeOszlop){
                torlesInfo.setHeaderText("Kattintson a törlendő oszlopba!");
                torlesInfo.showAndWait();
                jatek.segitseg();
                jatek.setTorlesSegitseg();
                pontszam.setText(Integer.toString(jatek.getPontszam()));
                oVSTorleseGomb.setText("Oszlop vagy sor törlés "+ jatek.getTorlesSegitseg() +"x");
                sorTorles = false;
                torlesSegitseg = true;
                kep1.setDisable(true);
                kep2.setDisable(true);
                kep3.setDisable(true);
            }
        }else{
            if (jatek.getTorlesSegitseg() == 0){
                nincsTobbSegitseg.show();
            }else {
                nincsElégPont.show();
            }
        }
    }

    public void tablaClick(MouseEvent mouseEvent) {
        int x = (int) (MouseInfo.getPointerInfo().getLocation().getX() - App.getStage().getX()) - 7;
        int y = (int) (MouseInfo.getPointerInfo().getLocation().getY() - App.getStage().getY()) - 70;
        int oszlop = (int) x / 40 + 1;
        int sor = (int) y / 40 + 1;
        if (torlesSegitseg){
            if (sorTorles){
                jatek.sorTorles(sor-1);
                megjelenit();
                kep1.setDisable(false);
                kep2.setDisable(false);
                kep3.setDisable(false);
                torlesSegitseg = false;
            }else {
                jatek.oszlopTorles(oszlop-1);
                megjelenit();
                kep1.setDisable(false);
                kep2.setDisable(false);
                kep3.setDisable(false);
                torlesSegitseg = false;
            }
            if (jatek.vesztettE()){
                vesztett();
            }
        }
        if(MainWindowController.getRekord() < jatek.getPontszam()){
            rekord.setText(Integer.toString(jatek.getPontszam()));
        } else {
            rekord.setText(Integer.toString(MainWindowController.getRekord()));
        }
    }

    private void vesztett(){
        Alert vege = new Alert(Alert.AlertType.INFORMATION);
        vege.setTitle("Vége");
        vege.setHeaderText("Vége a Játéknak!");
        vege.setContentText(MainWindowController.getRekord() >= jatek.getPontszam() ? jatek.getPontszam() + " pontot ért el! :)" : jatek.getPontszam() + " pontot ért el, ami új rekord! :)");
        jatek.setIdoVege(System.currentTimeMillis());
        vege.show();
        Eredmeny eredmeny = new Eredmeny();
        eredmeny.setName(MainWindowController.getJatekosFelhasznaloNev());
        eredmeny.setScore(jatek.getPontszam());
        eredmeny.setDate(java.time.LocalDate.now());
        eredmeny.setTime(idoAtalakit(jatek.getIdoKezdes(), jatek.getIdoVege()));
        dao.save(eredmeny);
        MainWindowController.setRekord(jatek.getPontszam());
        App.setMainWindow(FXMLLoader.loadFXML("/fxml/main_window.fxml"));
    }
}
