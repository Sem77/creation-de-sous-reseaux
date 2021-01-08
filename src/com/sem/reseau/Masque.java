package com.sem.reseau;
import com.sem.exception.ValeurHorsDePorteeException;
import com.sem.reseau.adresse.*;

public class Masque extends Adresse{

    public Masque(int premierOctet, int deuxiemeOctet, int troisiemeOctet, int quatriemeOctet) throws ValeurHorsDePorteeException{
        super(premierOctet, deuxiemeOctet, troisiemeOctet, quatriemeOctet);
    }

    public int nbBitsA1DansLeMasque(){
        String masqueBinConcat = Integer.toBinaryString(premierOctet) + Integer.toBinaryString(deuxiemeOctet) + Integer.toBinaryString(troisiemeOctet) + Integer.toBinaryString(quatriemeOctet);
        return masqueBinConcat.indexOf('0');
    }

    public int nbBitsA0DansLeMasque(){
        return 32 - nbBitsA1DansLeMasque();
    }

    public String toString(){
        String masque = "";
        masque += String.valueOf(premierOctet) + "." + String.valueOf(deuxiemeOctet) + "." + String.valueOf(troisiemeOctet) + "." + String.valueOf(quatriemeOctet);
        return masque;
    }
}
