//Algorytm zmieniajacy nazwe polegajacy na dopisaniu na koncu nazwy od 1 do 3 losowych znakow

package AlgorytmyModyfikacjiNazw;

import java.util.Random;

public class DopisanieZnakowSpecjalnych implements ModyfikatorNazw{

    @Override
    public String modyfikacjaNazwy(String nazwa) {

        Random rand = new Random();
        int iloscCyfr = rand.nextInt(3) + 1;
        for(int i = 0; i < iloscCyfr; i++) {
            char q = (char) ('!' + rand.nextInt(10));
            nazwa += q;
        }
        return nazwa;
    }
}
