package Menu;

import BazyUzytkownikow.BazaNr1;
import BazyUzytkownikow.BazaNr2;
import BazyUzytkownikow.BazaUzytkownikow;

import java.util.Objects;
import java.util.Scanner;

public class Menu {
    BazaNr1 bazaNr1;
    BazaNr2 bazaNr2;

    public Menu() {

        bazaNr1 = new BazaNr1(100);
        bazaNr2 = new BazaNr2(100);
    }

    public void uruchom() {

        System.out.printf("%40s %n", "MENU");
        System.out.printf("%57s %n", "1. Dodaj nowego uzytkownika do bazy nr 1");
        System.out.printf("%57s %n", "2. Dodaj nowego uzytkownika do bazy nr 2");
        System.out.printf("%58s %n", "3. Wyswietl dane uzytkownikow z bazy nr 1");
        System.out.printf("%58s %n", "4. Wyswietl dane uzytkownikow z bazy nr 2");
        System.out.printf("%50s %n", "5. Dokonaj serializacji bazy nr 1");
        System.out.printf("%50s %n", "6. Dokonaj serializacji bazy nr 2");
        System.out.printf("%46s", "7. Zakoncz dzialanie programu");

        Scanner scanner = new Scanner(System.in);
        String q = "";
        while(!Objects.equals(q, "7")) {
            System.out.printf("%n %n %40s" ,"Co chcesz zrobic? Podaj odpowiedni numer: ");
            q = scanner.nextLine();
            switch (q) {
                case "1":
                    nowyUzytkownik(bazaNr1);
                    break;
                case "2":
                    nowyUzytkownik(bazaNr2);
                    break;
                case "3":
                    bazaNr1.wyswietlDane();
                    break;
                case "4":
                    bazaNr2.wyswietlDane();
                    break;
                case "5":
                    bazaNr1.serializacja();
                    BazaNr1 bazaS1 = (BazaNr1) bazaNr1.deserializacja();
                    System.out.println(bazaS1.toString());
                    break;
                case "6":
                    bazaNr2.serializacja();
                    BazaNr2 bazaS2 = (BazaNr2) bazaNr2.deserializacja();
                    System.out.println(bazaS2.toString());
                    break;
                case "7":
                    System.out.println("Koncze dzialanie programu");
                    break;
                default:
                    System.out.println("Podano nieprawidlowy numer!");
            }
        }
    }


    public void nowyUzytkownik(BazaUzytkownikow baza) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj nazwe nowego uzytkownika: ");
        String nazwa = scanner.nextLine();
        while(!baza.sprawdzPoprawnoscCiagu(nazwa)) {
            System.out.print("Podaj prawidlowa nazwe: ");
            nazwa = scanner.nextLine();
        }
        while(baza.sprawdzCzyNazwaWystepuje(nazwa)) {
            String wolnaNazwa = baza.znajdzWolnaNazwe(nazwa);
            System.out.println("Podana nazwa uzytkownika jest juz zajeta!");
            System.out.println("Wymysl nowa lub wpisz \"tak\" aby zaakceptowac zmodyfikowana nazwe: " + wolnaNazwa);
            nazwa = scanner.nextLine();
            if(Objects.equals(nazwa, "tak")) nazwa = wolnaNazwa;
            while(!baza.sprawdzPoprawnoscCiagu(nazwa)) {
                System.out.print("Podaj prawidlowa nazwe: ");
                nazwa = scanner.nextLine();
            }
        }
        System.out.println("Nazwa uzytkownika zaakceptowana!");

        System.out.print("Podaj adres nowego uzytkownika (bez koncowki \"gmail.com\": ");
        String adres = scanner.nextLine().toLowerCase();
        while(!baza.sprawdzPoprawnoscCiagu(adres)) {
            System.out.print("Podaj prawidlowy adres: ");
            adres = scanner.nextLine();
        }
        while(baza.sprawdzCzyAdresWystepuje(adres)) {
            String wolnyAdres = baza.znajdzWolnyAdres(adres);
            System.out.println("Podany adres e-mail jest juz zajety!");
            System.out.println("Wymysl nowy lub wpisz \"tak\" aby zaakceptowac zmodyfikowany adres e-mail: " + wolnyAdres);
            adres = scanner.nextLine().toLowerCase();
            if(Objects.equals(adres, "tak")) adres = wolnyAdres;
            while(!baza.sprawdzPoprawnoscCiagu(adres)) {
                System.out.print("Podaj prawidlowy adres: ");
                adres = scanner.nextLine();
            }
        }
        System.out.println("Adres e-mail zaakceptowany!");
        baza.dodajNowegoUzytkownika(nazwa, adres);
        System.out.println("Pomyslnie dodano nowego uzytkownika o podanej nazwie i adresie e-mail: " +
                nazwa + ", " + adres + "@gmail.com");
    }
}
