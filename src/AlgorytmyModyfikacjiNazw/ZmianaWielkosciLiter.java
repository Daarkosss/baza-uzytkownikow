//Algorytm zmieniajacy nazwe polegajacy na zmianie wielkosci losowej ilosci liter

package AlgorytmyModyfikacjiNazw;

import java.util.Random;

public class ZmianaWielkosciLiter implements ModyfikatorNazw{
    @Override
    public String modyfikacjaNazwy(String nazwa) {
        StringBuilder temp = new StringBuilder(nazwa);
        Random rand = new Random();
        for(int i = 0; i < nazwa.length(); i++) {
            boolean t = rand.nextBoolean();
            char q = temp.charAt(i);
            if(t && ((q >= 'a' && q <= 'z') || (q >= 'A' && q <= 'Z'))) {
                if(q <= 'Z') q += 32;
                else q -= 32;
                temp.setCharAt(i, q);
            }
        }
        nazwa = temp.toString();
        return nazwa;
    }
}
