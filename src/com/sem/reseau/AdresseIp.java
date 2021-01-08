package com.sem.reseau;

import com.sem.exception.ValeurHorsDePorteeException;
import com.sem.reseau.adresse.*;

public class AdresseIp extends Adresse {
    public AdresseIp(int premierOctet, int deuxiemeOctet, int troisiemeOctet, int quatriemeOctet)throws ValeurHorsDePorteeException {
        super(premierOctet, deuxiemeOctet, troisiemeOctet, quatriemeOctet);
    }

    public char classeIp(){
        char classe = 'A';
        String premierOctetBinaire = formeBinaire.substring(0, 8);
        if(premierOctetBinaire.substring(0, 2).equals("00"))
            classe = 'A';
        else if(premierOctetBinaire.substring(0, 2).equals("10"))
            classe = 'B';
        else if(premierOctetBinaire.substring(0, 3).equals("110"))
            classe = 'C';
        else if(premierOctetBinaire.substring(0, 4).equals("1110"))
            classe = 'D';
        else if(premierOctetBinaire.substring(0, 4).equals("1111"))
            classe = 'E';

        return classe;
    }

    public String toString(){
        String ip = "";
        ip += String.valueOf(premierOctet) + "." + String.valueOf(deuxiemeOctet) + "." + String.valueOf(troisiemeOctet) + "." + String.valueOf(quatriemeOctet);
        return ip;
    }
}
