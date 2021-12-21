//Algorytm zmiany nazwy polegajacy na zamianie miedzy soba dwoch losowych znakow

package AlgorytmyModyfikacjiAdresowMailowych;

import java.util.Random;

public class ZamianaZnakow implements ModyfikatorAdresow{
    @Override
    public String modyfikacjaAdresu(String nazwa) {
        StringBuilder temp = new StringBuilder(nazwa);
        Random rand = new Random();
        int i = rand.nextInt(nazwa.length());
        int j = i;
        while(i == j) {
            j = rand.nextInt(nazwa.length());
        }
        char q = temp.charAt(i);
        temp.setCharAt(i, temp.charAt(j));
        temp.setCharAt(j, q);

        nazwa = temp.toString();
        return nazwa;
    }
}
