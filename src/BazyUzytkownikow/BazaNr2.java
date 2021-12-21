package BazyUzytkownikow;

import AlgorytmyModyfikacjiAdresowMailowych.ZamianaZnakow;
import AlgorytmyModyfikacjiNazw.ZmianaWielkosciLiter;

public class BazaNr2 extends BazaUzytkownikow{
    public BazaNr2(int n) {
        super(n);

        modyfikatorNazw = new ZmianaWielkosciLiter();
        setAlgorytmInfoModyfikatorNazw("Zmiana wielkosci liter");

        modyfikatorAdresow = new ZamianaZnakow();
        setAlgorytmInfoModyfikatorAdresow("Zamiana Znakow");
    }
}