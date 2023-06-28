package com.example.dentisttestapp;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import com.jfoenix.controls.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    @FXML
    public JFXButton resultBtn;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXComboBox<String> gender;

    @FXML
    private JFXComboBox<Item> age;

    @FXML
    private JFXComboBox<Item> smoking;

    @FXML
    private JFXComboBox<Item> diabetes;

    @FXML
    private JFXComboBox<Item> bleeding;

    @FXML
    private JFXComboBox<Item> boneLoss;

    @FXML
    private JFXComboBox<Item> lossOfDentalAttachment;

    @FXML
    private JFXComboBox<Item> pocketsNumber;

    @FXML
    private JFXComboBox<Item> destructiveTeethNumber;

    @FXML
    private JFXComboBox<Item> microorganismsPresence;

    @FXML
    private JFXComboBox<Item> microorganismsAssociationsPresence;


    @FXML
    protected void onResultButtonClick() throws MalformedURLException {
        if(name.getText().length() < 1){
            name.setStyle("-fx-background-color: #ed9a9a");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("result-view.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(loader.load(), 450, 530));
            stage.setTitle("Прогнозирование вероятности развития болезней периодонта");
            stage.getIcons().add(new Image("icon.png"));
            stage.setResizable(false);
            ResultController controller = loader.getController();
            controller.initData(calculateResult(), name.getText());

            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onEnter(ActionEvent ae){
        System.out.println("test");
        name.setStyle("-fx-background-color: #FFFFFF");
    }

    private int calculateResult(){
        return (smoking.getValue().getPoints() + diabetes.getValue().getPoints()
        + bleeding.getValue().getPoints() + boneLoss.getValue().getPoints()
        + lossOfDentalAttachment.getValue().getPoints() + pocketsNumber.getValue().getPoints()
        + destructiveTeethNumber.getValue().getPoints() + microorganismsPresence.getValue().getPoints()
        + microorganismsAssociationsPresence.getValue().getPoints());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.getItems().addAll("Мужской", "Женский");

        age.getItems().addAll(
                new Item(0, "0-25"), new Item(2, "26-40"),
                new Item(4, "41-50"),new Item(6, "51-65"),
                new Item(8, ">65"));
        smoking.getItems().addAll(
                new Item(0, "Никогда не курил"), new Item(1, "Курил, но бросил"),
                new Item(2, "1-9 сигарет в день"), new Item(3, "10-19 сигарет в день"),
                new Item(4, "≥20 сигарет в день"));
        diabetes.getItems().addAll(
                new Item(0, "Нет диабета"),
                new Item(1, "Контролируемый диабет"),
                new Item(2, "Неконтролируемый диабет"));
        bleeding.getItems().addAll(
                new Item(0, "0–1"), new Item(1, "2-4"),
                new Item(2, "5-7"), new Item(3, "8-10"),
                new Item(4, ">10"));
        boneLoss.getItems().addAll(
                new Item(0, "0"), new Item(8, "1-3"),
                new Item(8, "4-6"), new Item(8, "7-10"),
                new Item(8, ">10"));
        lossOfDentalAttachment.getItems().addAll(
                new Item(0, "0–1"), new Item(1, "2-4"),
                new Item(2, "5-7"), new Item(3, "8-10"),
                new Item(4, ">10"));
        pocketsNumber.getItems().addAll(
                new Item(0,"0–1"), new Item(1, "2-4"),
                new Item(2, "5-7"), new Item(3, "8-10"),
                new Item(4, ">10"));
        destructiveTeethNumber.getItems().addAll(
                new Item(0, "0-1"), new Item(1, "2-4"),
                new Item(2, "5-7"), new Item(3, "8-10"),
                new Item(4, "≥11"));
        microorganismsPresence.getItems().addAll(
                new Item(0, "Не обнаружено"),
                new Item(0, "Treponema denticolа"),
                new Item(0, "Prevotella intermedia"),
                new Item(1, "Porphyromonas gingivalis"),
                new Item(1, "Aggretibacter actinomycetemcomitans"),
                new Item(1, "Tannerella forsythia"));
        microorganismsAssociationsPresence.getItems().addAll(
                new Item(0, "Не обнаружено"), new Item(1, "Обнаружено"));

        gender.setValue("Мужской");
        age.getSelectionModel().selectFirst();
        smoking.getSelectionModel().selectFirst();
        diabetes.getSelectionModel().selectFirst();
        bleeding.getSelectionModel().selectFirst();
        boneLoss.getSelectionModel().selectFirst();
        lossOfDentalAttachment.getSelectionModel().selectFirst();
        pocketsNumber.getSelectionModel().selectFirst();
        destructiveTeethNumber.getSelectionModel().selectFirst();
        microorganismsPresence.getSelectionModel().selectFirst();
        microorganismsAssociationsPresence.getSelectionModel().selectFirst();

        age.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            List<Item> list = boneLoss.getItems();
            int points = 0;

            switch (newValue.getDescription()) {
                case ("0-25") -> points = 8;
                case ("26-40") -> points = 6;
                case ("41-50") -> points = 4;
                case ("51-65") -> points = 2;
                case (">65") -> points = 0;
            }

            for ( Item item : list.subList( 1, list.size())){
                item.setPoints(points);
            }
        });

        name.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                name.setStyle("-fx-background-color: #FFFFFF00");
            }
        });

    }


}