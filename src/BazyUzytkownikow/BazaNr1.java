package BazyUzytkownikow;

import AlgorytmyModyfikacjiAdresowMailowych.DopisanieCyfr;
import AlgorytmyModyfikacjiNazw.DopisanieZnakowSpecjalnych;

import java.io.Serializable;

public class BazaNr1 extends BazaUzytkownikow implements Serializable {
    public BazaNr1(int n) {
        super(n);

        modyfikatorNazw = new DopisanieZnakowSpecjalnych();
        setAlgorytmInfoModyfikatorNazw("Dopisanie znakow specjalnych");

        modyfikatorAdresow = new DopisanieCyfr();
        setAlgorytmInfoModyfikatorAdresow("Dopisanie Cyfr");
    }
}
