package hr.tvz.java.tjedan03.nogomet.aplikacija;

import hr.tvz.java.tjedan03.nogomet.apstraktne.Gradjevina;
import hr.tvz.java.tjedan03.nogomet.enumeracije.Pozicija;
import hr.tvz.java.tjedan03.nogomet.klase.*;
import hr.tvz.java.tjedan03.nogomet.zapisi.Adresa;
import hr.tvz.java.tjedan03.nogomet.pomocne.ExceptionHandler;

import java.time.Year;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExceptionHandler handler = new ExceptionHandler(sc);


        int brojKlubova = handler.unesiCijeliBroj("Koliko klubova želite unijeti: ",1,1000);
        NogometniKlub[] klubovi = new NogometniKlub[brojKlubova];

        for (int i = 0; i < brojKlubova; i++) {
            System.out.println("\n===== UNOS KLUBA " + (i + 1) + " =====");

            String naziv = handler.unesiObavezanTekst("Naziv kluba: ");
            String grad = handler.unesiObavezanTekst("Gra: ");
            int godinaOsnivanja = handler.unesiCijeliBroj("Godina osnivanja: ");
            System.out.println();

            //trener
            String imeTrenera = handler.unesiObavezanTekst("Ime trenera: ");
            String prezimeTrenera = handler.unesiObavezanTekst("Prezime trenera: ");
            int godinaRođenja = handler.unesiCijeliBroj("Godina rođenja: ",1, Year.now().getValue());
            int godineiskustva = handler.unesiCijeliBroj("Godine iskustva: ",1, Year.now().getValue());

            Trener privremeniTrener = new Trener(imeTrenera, prezimeTrenera, godinaRođenja, godineiskustva);

            //upis igraca
            int brojIgraca = handler.unesiCijeliBroj("\nKoliko igrača želite unijeti: ",0 ,1000);
            Igrac[] igraci = new Igrac[brojIgraca];

            for (int j = 0; j < brojIgraca; j++) {
                System.out.println("\n--- IGRAČ " + (j + 1) + " ---");

                String ime = handler.unesiObavezanTekst("Ime: ");
                String prezime = handler.unesiObavezanTekst("Prezime: ")
                int godinaRodenja = handler.unesiCijeliBroj("Godina rođenja: " ,1, Year.now().getValue());
                int brojDresa = handler.unesiCijeliBroj("Broj dresa: ",1,1000);
                Pozicija pozicija = handler.unesiPoziciju("Pozicija (1 - VRATAR, 2 - BRANIC, 3 - VEZNJAK, 4 - NAPADAC): ");

                igraci[j] = new Igrac(ime, prezime, godinaRodenja, brojDresa, pozicija);
            }


            System.out.print("\nKoliko građevina želite unijeti: ");
            int brojGradevina = Integer.parseInt(sc.nextLine());
            Gradjevina[] gradjevina = new Gradjevina[brojGradevina];
            for (int j = 0; j < brojGradevina; j++) {
                System.out.println("\n--- GRAĐEVINA " + (j + 1) + " ---");
                int vrstaGradevine;
                while (true) {
                    System.out.print("Vrsta (1 - Stadion, 2 - MedicinskaOrdinacija, 3 - SportskiDom): ");
                    vrstaGradevine = Integer.parseInt(sc.nextLine());
                    if (vrstaGradevine < 1 || vrstaGradevine > 3) {
                        System.out.println("Upisana građevina mora biti od 1 - 3");
                    } else {
                        break;
                    }
                }

                System.out.print("Naziv: ");
                String nazivGradjevine = sc.nextLine();
                System.out.print("Ulica: ");
                String ulicaGradjevine = sc.nextLine();
                System.out.print("Kućni broj: ");
                String kucniGradjevine = sc.nextLine();
                System.out.print("Grad: ");
                String gradGradjevine = sc.nextLine();

                Adresa adresaGradjevine = new Adresa(ulicaGradjevine, kucniGradjevine, gradGradjevine);
                switch (vrstaGradevine) {
                    case 1:
                        System.out.print("Kapacitet: ");
                        int kapacitetGradjevine = Integer.parseInt(sc.nextLine());
                        System.out.print("Godina izgradnje: ");
                        int godinaGodinaGradjevine = Integer.parseInt(sc.nextLine());
                        System.out.print("Natkriven (true/false): ");
                        boolean natkriven = Boolean.parseBoolean(sc.nextLine());
                        gradjevina[j] = new Stadion(adresaGradjevine, nazivGradjevine, kapacitetGradjevine, godinaGodinaGradjevine, natkriven);
                        break;
                    case 2:
                        System.out.print("Broj ambulanti: ");
                        int brojAmbulantiGradjevine = Integer.parseInt(sc.nextLine());
                        System.out.print("Ima hitnu službu (true/false): ");
                        boolean imaHitnuSluzbu = Boolean.parseBoolean(sc.nextLine());
                        gradjevina[j] = new MedicinskaOrdinacija(adresaGradjevine, nazivGradjevine, brojAmbulantiGradjevine, imaHitnuSluzbu);
                        break;
                    case 3:
                        System.out.print("Broj kreveta: ");
                        int brojKrevetaGradjevine = Integer.parseInt(sc.nextLine());
                        System.out.print("Ima blagovaonicu (true/false): ");
                        boolean imaBlagovaonicu = Boolean.parseBoolean(sc.nextLine());
                        gradjevina[j] = new SportskiDom(adresaGradjevine, nazivGradjevine, brojKrevetaGradjevine, imaBlagovaonicu);
                        break;
                    default:
                        break;
                }

            }
            klubovi[i] = new NogometniKlub(naziv, grad, godinaOsnivanja, gradjevina, privremeniTrener, igraci);

        }

        int odabir;
        do {
            System.out.println("\n===== NOGOMETNI KLUBOVI =====");
            System.out.println("1 - Pretraži klub");
            System.out.println("0 - Završi program");
            System.out.print("Odabir: ");
            odabir = Integer.parseInt(sc.nextLine());

            if (odabir == 1) {
                System.out.print("Unesite naziv kluba: ");
                String naziv = sc.nextLine();
                boolean pronaden = false;
                for (NogometniKlub nk : klubovi) {
                    if (nk.getNaziv().equalsIgnoreCase(naziv)) {
                        pronaden = true;
                        System.out.println(nk.getOpis());
                    }
                }
                if (!pronaden) {
                    System.out.println("Nema kluba s tim nazivom.");
                }
            } else if (odabir == 0) {
                System.out.println("Program završava.");
            } else {
                System.out.println("Ovo nije opcija.");
            }
        } while (odabir != 0);


    }
}
