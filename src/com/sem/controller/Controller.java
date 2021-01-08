package com.sem.controller;

import com.sem.exception.ValeurHorsDePorteeException;
import com.sem.reseau.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller implements Initializable{

    @FXML
    private TextField premierOctetIp, deuxiemeOctetIp, troisiemeOctetIp, quatriemeOctetIp, nbSR;
    @FXML
    private TextField premierOctetMasque, deuxiemeOctetMasque, troisiemeOctetMasque, quatriemeOctetMasque;
    @FXML
    private Button boutonDecouper;
    @FXML
    private TextArea zoneTexte;
    @FXML
    private RadioButton radioButtonReseau, radioButtonSR;
    public ToggleGroup tg = new ToggleGroup();


    @FXML
    protected void remplirMasque() throws ValeurHorsDePorteeException{

        try{
            AdresseIp ip = new AdresseIp(Integer.parseInt(premierOctetIp.getText()), Integer.parseInt(deuxiemeOctetIp.getText()), Integer.parseInt(troisiemeOctetIp.getText()), Integer.parseInt(quatriemeOctetIp.getText()));
            char classe = ip.classeIp();
            if(classe == 'A'){
                premierOctetMasque.setText("255");
                deuxiemeOctetMasque.setText("0");
                troisiemeOctetMasque.setText("0");
                quatriemeOctetMasque.setText("0");
            }
            else if(classe == 'B'){
                premierOctetMasque.setText("255");
                deuxiemeOctetMasque.setText("255");
                troisiemeOctetMasque.setText("0");
                quatriemeOctetMasque.setText("0");
            }
            else if(classe == 'C'){
                premierOctetMasque.setText("255");
                deuxiemeOctetMasque.setText("255");
                troisiemeOctetMasque.setText("255");
                quatriemeOctetMasque.setText("0");
            }
            premierOctetMasque.setDisable(true);
            deuxiemeOctetMasque.setDisable(true);
            troisiemeOctetMasque.setDisable(true);
            quatriemeOctetMasque.setDisable(true);
        }catch(NumberFormatException e1){
            zoneTexte.clear();
            zoneTexte.setText("Adresse ip manquante");
        }catch(ValeurHorsDePorteeException e2){
            zoneTexte.clear();
            zoneTexte.setText("La valeur d'un octet doit être entre 0 et 255");
        }
    }

    @FXML
    protected void viderMasque() throws ValeurHorsDePorteeException{
        try{
            AdresseIp ip = new AdresseIp(Integer.parseInt(premierOctetIp.getText()), Integer.parseInt(deuxiemeOctetIp.getText()), Integer.parseInt(troisiemeOctetIp.getText()), Integer.parseInt(quatriemeOctetIp.getText()));
            char classe = ip.classeIp();

            if(classe == 'A'){
                premierOctetMasque.setText("255");
                premierOctetMasque.setDisable(true);
                deuxiemeOctetMasque.setDisable(false);
                troisiemeOctetMasque.setDisable(false);
                quatriemeOctetMasque.setDisable(false);
            }
            else if(classe == 'B'){
                premierOctetMasque.setText("255");
                deuxiemeOctetMasque.setText("255");
                premierOctetMasque.setDisable(true);
                deuxiemeOctetMasque.setDisable(true);
                troisiemeOctetMasque.setDisable(false);
                quatriemeOctetMasque.setDisable(false);
            }
            else if(classe == 'C'){
                premierOctetMasque.setText("255");
                deuxiemeOctetMasque.setText("255");
                troisiemeOctetMasque.setText("255");
                premierOctetMasque.setDisable(true);
                deuxiemeOctetMasque.setDisable(true);
                troisiemeOctetMasque.setDisable(true);
                quatriemeOctetMasque.setDisable(false);
            }
        }catch(NumberFormatException e){
            zoneTexte.clear();
            zoneTexte.setText("Adresse ip manquante");
        }catch(ValeurHorsDePorteeException e2){
            zoneTexte.clear();
            zoneTexte.setText("La valeur d'un octet doit être entre 0 et 255");
        }
    }

    @FXML
    protected void decoupage() throws ValeurHorsDePorteeException{
        zoneTexte.clear();

        AdresseIp ip = new AdresseIp(Integer.parseInt(premierOctetIp.getText()), Integer.parseInt(deuxiemeOctetIp.getText()), Integer.parseInt(troisiemeOctetIp.getText()), Integer.parseInt(quatriemeOctetIp.getText()));
        Masque masque = new Masque(Integer.parseInt(premierOctetMasque.getText()), Integer.parseInt(deuxiemeOctetMasque.getText()), Integer.parseInt(troisiemeOctetMasque.getText()), Integer.parseInt(quatriemeOctetMasque.getText()));
        
        Reseau monReseau = new Reseau(ip, masque);
        int nbDeSR = Integer.parseInt(nbSR.getText());

        if(monReseau.decouperEnSR(nbDeSR) == true){
            for (int i = 0; i < nbDeSR; i++) {
                zoneTexte.appendText(monReseau.tabSR[i].toString() + "\n");
                zoneTexte.appendText("Broadcast: " + monReseau.tabSR[i].adresseDiffusion() + "\n");
                zoneTexte.appendText("Nombre d'hôtes: " + String.valueOf(monReseau.tabSR[i].nbHotesDispo()) + "\n");
                zoneTexte.appendText("-------------------------------------------\n");
            }
        }
        else{
            zoneTexte.clear();
            zoneTexte.appendText("Nombre de sous réseau trop grand");
        }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		zoneTexte.setText("");
		
    }
}
