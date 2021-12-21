package BazyUzytkownikow;

import AlgorytmyModyfikacjiNazw.ModyfikatorNazw;
import AlgorytmyModyfikacjiAdresowMailowych.ModyfikatorAdresow;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public abstract class BazaUzytkownikow implements Serializable {

    transient protected ModyfikatorNazw modyfikatorNazw;
    transient protected ModyfikatorAdresow modyfikatorAdresow;
    private String AlgorytmInfoModyfikatorNazw;
    private String AlgorytmInfoModyfikatorAdresow;

    private String[] nazwy;
    private String[] adresy;
    private int iloscUzytkownikow;

    public BazaUzytkownikow() {
        modyfikatorNazw = null;
        nazwy = null;
        adresy = null;
        iloscUzytkownikow = 0;
    }

    public BazaUzytkownikow(int n) {
        nazwy = new String[n];
        adresy = new String[n];
        iloscUzytkownikow = 0;
    }

    public String wykonajModyfikacjeNazwy(String nazwa) {
        return modyfikatorNazw.modyfikacjaNazwy(nazwa);
    }

    public void ustawModyfikatorNazw(ModyfikatorNazw modyfikatorNazw) {
        this.modyfikatorNazw = modyfikatorNazw;
    }

    public String wykonajModyfikacjeAdresu(String nazwa) {
        return modyfikatorAdresow.modyfikacjaAdresu(nazwa);
    }

    public void ustawModyfikatorAdresow(ModyfikatorAdresow modyfikatorAdresow) {
        this.modyfikatorAdresow = modyfikatorAdresow;
    }

    public int getiloscUzytkownikow() {
        return iloscUzytkownikow;
    }

    public void setiloscUzytkownikow(int iloscUzytkownikow) {
        this.iloscUzytkownikow = iloscUzytkownikow;
    }

    public String[] getNazwy() {
        return nazwy;
    }

    public int iloscLiter(String s) {
        StringBuilder sb = new StringBuilder(s);
        int ilosc = 0;
        for(int i = 0; i < sb.length(); i++) {
            int q = sb.charAt(i);
            if ((q >= 'a' && q <= 'z') || (q >= 'A' && q <= 'Z')) ilosc++;
        }
        return ilosc;
    }

    public boolean sprawdzPoprawnoscCiagu(String s) {
        boolean t = true;
        if(s.length() < 5) {
            System.out.println("Podany ciag znakow jest za krotki! (minimum 5 znakow)");
            t = false;
        }

        if(s.length() > 20) {
            System.out.println("Podany ciag znakow jest za dlugi! (maksimum 20 znakow)");
            t = false;
        }
        if(iloscLiter(s) < 3) {
            System.out.println("W podanym ciagu znakow jest za malo liter! (minimum 3 litery)");
            t = false;
        }
        if(s.contains(" ")) {
            System.out.println("Nazwa/adres nie moze zawierac znakow bialych (spacji, tabulatorow itp.)!");
            t = false;
        }

        return t;
    }

    public void dodajNowegoUzytkownika(String nazwa, String adres) {
        nazwy[iloscUzytkownikow] = nazwa;
        adresy[iloscUzytkownikow] = adres;
        iloscUzytkownikow++;
    }

    public String znajdzWolnaNazwe(String nazwa) {
        while(sprawdzCzyNazwaWystepuje(nazwa)) {
            nazwa = wykonajModyfikacjeNazwy(nazwa);
        }
        return nazwa;
    }

    public String znajdzWolnyAdres(String adres) {
        while(sprawdzCzyAdresWystepuje(adres)) {
            adres = wykonajModyfikacjeAdresu(adres);
        }
        return adres;
    }

    public boolean sprawdzCzyNazwaWystepuje(String nazwa) {
        for(int i = 0; i < iloscUzytkownikow; i++) {
            if(Objects.equals(nazwy[i], nazwa)) return true;
        }
        return false;
    }

    public boolean sprawdzCzyAdresWystepuje(String adres) {
        for(int i = 0; i < iloscUzytkownikow; i++) {
            if(Objects.equals(adresy[i], adres)) return true;
        }
        return false;
    }

    public void wyswietlDane() {
        if(iloscUzytkownikow == 0) {
            System.out.println("Ta baza uzytkownikow jest pusta!");
            return;
        }
        System.out.printf("%3s %20s %30s %n", "Lp.", "Nazwa", "Adres e-mail");
        for(int i = 0; i < iloscUzytkownikow; i++) {
            System.out.printf("%2d. %20s %20s%s %n", i+1, nazwy[i], adresy[i], "@gmail.com");
        }
    }

    public String nazwaKlasy() {

        if(this instanceof BazaNr1) return "bazaNr1.bin";
        else if(this instanceof  BazaNr2) return "bazaNr2.bin";
        else return null;
    }

    public void serializacja() {

        String nazwaPliku = nazwaKlasy();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nazwaPliku))) {
            outputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BazaUzytkownikow deserializacja() {

        String nazwaPliku = nazwaKlasy();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nazwaPliku))) {
            BazaUzytkownikow baza = (BazaUzytkownikow) inputStream.readObject();
            return baza;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "BazaUzytkownikow (" +
                "AlgorytmInfoModyfikatorNazw = " + AlgorytmInfoModyfikatorNazw +
                ", AlgorytmInfoModyfikatorAdresow = " + AlgorytmInfoModyfikatorAdresow + ")\n" +
                "IloscUzytkownikow = " + iloscUzytkownikow + "\n" +
                daneUzytkownikowToString() + "\n";

    }

    public String daneUzytkownikowToString() {
        String dane = String.format("%20s %20s %n", "Nazwa uzytkownika:", "Adres e-mail:");
        for(int i = 0 ; i < iloscUzytkownikow; i++) {
            dane += String.format("%20s %20s %n", nazwy[i], adresy[i]);
        }

        return dane;
    }

    public String getAlgorytmInfoModyfikatorNazw() {
        return this.AlgorytmInfoModyfikatorNazw;
    }

    public void setAlgorytmInfoModyfikatorNazw(String s) {
        this.AlgorytmInfoModyfikatorNazw = s;
    }

    public String getAlgorytmInfoModyfikatorAdresow() {
        return this.AlgorytmInfoModyfikatorAdresow;
    }

    public void setAlgorytmInfoModyfikatorAdresow(String s) {
        this.AlgorytmInfoModyfikatorAdresow = s;
    }
}
