package com.sem.reseau;

import com.sem.exception.ValeurHorsDePorteeException;

public class Reseau {
    public AdresseIp ip;
    public Masque masque;
    public Reseau tabSR[] = new Reseau[100];

    public Reseau(AdresseIp ip, Masque masque){
        this.ip = ip;
        this.masque = masque;
    }

    public Reseau(AdresseIp ip, char classe) throws ValeurHorsDePorteeException{
        //Assignement de l'adresse ip
        this.ip = ip;

        //Assignement du masque
        if(classe == 'A')
            masque = new Masque(255, 0, 0, 0);
        else if(classe == 'B')
            masque = new Masque(255, 255, 0, 0);
        else if(classe == 'C')
            masque = new Masque(255, 255, 255, 0);
        else if(classe == 'D' || classe == 'E')
            masque = new Masque(255, 255, 255, 255);
    }

    //Avoir le nombre d'hôtes disponibles sur le réseau
    public int nbHotesDispo(){
        return Double.valueOf(Math.pow(2, masque.nbBitsA0DansLeMasque())).intValue() - 2;
    }

    //Nombre de bits nécessaires pour découper en SR
    private int bitsNecessaire(int nbSR){
        int nbBitsEmpruntes = Double.valueOf(Math.log(nbSR)/ Math.log(2)).intValue();
        if(nbSR % 2 == 0)
            return nbBitsEmpruntes;
        else
            return nbBitsEmpruntes + 1;
    }

    //Formation du masque des SR
    private Masque formationMasqueDesSR(int nbBitsEmpruntes) throws ValeurHorsDePorteeException{
        String masqueBin = masque.formeBinaire.substring(0, masque.nbBitsA1DansLeMasque());
        for(int i=0; i<nbBitsEmpruntes; i++){masqueBin += "1";}
        int nb0AAjouter = 32 - masqueBin.length();
        for(int i=0; i<nb0AAjouter; i++){masqueBin += "0";}
        Masque masqueSR = new Masque(Integer.parseInt(masqueBin.substring(0, 8), 2), Integer.parseInt(masqueBin.substring(8, 16), 2), Integer.parseInt(masqueBin.substring(16, 24), 2), Integer.parseInt(masqueBin.substring(24, 32), 2));
        
        return masqueSR;
    }

    //Formation de l'adresse Ip du SR
    private AdresseIp formationIpDesSR(int nb, int nbBitsEmpruntes) throws ValeurHorsDePorteeException{
        String ipBin = ip.formeBinaire.substring(0, masque.nbBitsA1DansLeMasque()) + intToBinSurNOctets(nb, nbBitsEmpruntes);
        int nb0AAjouter = 32 - ipBin.length();
        for(int i=0; i<nb0AAjouter; i++){ipBin += "0";}
        AdresseIp ipSR = new AdresseIp(Integer.parseInt(ipBin.substring(0, 8), 2), Integer.parseInt(ipBin.substring(8, 16), 2), Integer.parseInt(ipBin.substring(16, 24), 2), Integer.parseInt(ipBin.substring(24, 32), 2));
        
        return ipSR;
    }

    //Découper en sous réseaux
    public boolean decouperEnSR(int nbSR) throws ValeurHorsDePorteeException{
        int nbBitsEmpruntes = bitsNecessaire(nbSR);
        if(masque.nbBitsA0DansLeMasque() - nbBitsEmpruntes >= 0){
            for(int i=0; i<nbSR; i++)
                tabSR[i] = new Reseau(formationIpDesSR(i, nbBitsEmpruntes), formationMasqueDesSR(nbBitsEmpruntes));
        }
        else{
            return false;
        }
        return true;
    }

    //Adresse de réseau
    public String adresseReseau(){
        return toString();
    }

    //Adresse de diffusion
    public String adresseDiffusion(){
        String diffBinaire = ip.formeBinaire.substring(0, masque.nbBitsA1DansLeMasque()) + ip.formeBinaire.substring(masque.nbBitsA1DansLeMasque()).replace('0', '1');
        String diffDec = String.valueOf(Integer.parseInt(diffBinaire.substring(0, 8), 2)) + "." + String.valueOf(Integer.parseInt(diffBinaire.substring(8, 16), 2)) + "." + String.valueOf(Integer.parseInt(diffBinaire.substring(16, 24), 2)) + "." + String.valueOf(Integer.parseInt(diffBinaire.substring(24, 32), 2));
        
        return diffDec;
    }

    //Convertion d'un entier en binaire sur n octets
    private String intToBinSurNOctets(int entier, int nbOctets){
        String b = Integer.toBinaryString(entier);
        int t = nbOctets - b.length();
        for(int j=0; j<t; j++)
            b = "0" + b;
        return b;
    }

    public String toString(){
        String adresse = String.valueOf(ip.premierOctet) + "." + String.valueOf(ip.deuxiemeOctet) + "." + String.valueOf(ip.troisiemeOctet) + "." + String.valueOf(ip.quatriemeOctet) + "/" + String.valueOf(masque.nbBitsA1DansLeMasque());
        return adresse;
    }
}
