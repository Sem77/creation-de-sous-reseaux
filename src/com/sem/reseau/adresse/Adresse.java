package com.sem.reseau.adresse;
import com.sem.exception.*;

abstract public class Adresse{
    public int premierOctet, deuxiemeOctet, troisiemeOctet, quatriemeOctet;
    public String formeBinaire = "";

    public Adresse(int premierOctet, int deuxiemeOctet, int troisiemeOctet, int quatriemeOctet) throws ValeurHorsDePorteeException{
        this.premierOctet = premierOctet;
        this.deuxiemeOctet = deuxiemeOctet;
        this.troisiemeOctet = troisiemeOctet;
        this.quatriemeOctet = quatriemeOctet;
        if(premierOctet<0 || premierOctet>255 ||deuxiemeOctet<0 || deuxiemeOctet>255 || troisiemeOctet<0 || troisiemeOctet>255 || quatriemeOctet<0 || quatriemeOctet>255){
            throw new ValeurHorsDePorteeException();
        }

        int tabOctets[] = {premierOctet, deuxiemeOctet, troisiemeOctet, quatriemeOctet};        
        for(int i=0; i<tabOctets.length; i++){
            String b = Integer.toBinaryString(tabOctets[i]);
            int t = 8 - b.length();
            for(int j=0; j<t; j++)
                b = "0" + b;
            formeBinaire += b;
        }
    }
}
