//Algorytm zmieniajacy nazwe polegajacy na dopisaniu na koncu nazwy od 1 do 3 losowych cyfr

package AlgorytmyModyfikacjiAdresowMailowych;

import java.util.Random;

public class DopisanieCyfr implements ModyfikatorAdresow{
    @Override
    public String modyfikacjaAdresu(String nazwa) {
        Random rand = new Random();
        int iloscCyfr = rand.nextInt(3) + 1;
        for(int i = 0; i < iloscCyfr; i++) {
            char q = (char) ('0' + rand.nextInt(10));
            nazwa += q;
        }
        return nazwa;
    }
}
