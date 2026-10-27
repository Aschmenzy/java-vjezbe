package hr.tvz.java.tjedan02.nogomet.aplikacija;

import hr.tvz.java.tjedan02.nogomet.apstraktne.Gradjevina;
import hr.tvz.java.tjedan02.nogomet.enumeracije.Pozicija;
import hr.tvz.java.tjedan02.nogomet.klase.*;
import hr.tvz.java.tjedan02.nogomet.zapisi.Adresa;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Koliko klubova želite unijeti: ");
        int brojKlubova = Integer.parseInt(sc.nextLine());
        NogometniKlub[] klubovi = new NogometniKlub[brojKlubova];

        for (int i = 0; i < brojKlubova; i++) {
            System.out.println("=== UNOS KLUBA " + (i + 1) + " ===");

            System.out.print("Naziv kluba: ");
            String naziv = sc.nextLine();
            System.out.print("Grad: ");
            String grad = sc.nextLine();
            System.out.print("Godina osnivanja: ");
            int godinaOsnivanja = Integer.parseInt(sc.nextLine());
            System.out.println();
            //trener
            System.out.println();
            System.out.print("Ime trenera: ");
            String imeTrenera = sc.nextLine();
            System.out.print("Prezime trenera: ");
            String prezimeTrenera = sc.nextLine();
            System.out.print("Godina rođenja: ");
            int godinaRođenja = Integer.parseInt(sc.nextLine());
            System.out.print("Godine iskustva: ");
            int godineiskustva = Integer.parseInt(sc.nextLine());

            Trener privremeniTrener = new Trener(imeTrenera, prezimeTrenera, godinaRođenja, godineiskustva);

            //upis igraca
            System.out.print("\n Koliko igrača želite unijeti: ");
            int brojIgraca = Integer.parseInt(sc.nextLine());
            Igrac[] igraci = new Igrac[brojIgraca];

            for (int j = 0; j < brojIgraca; j++) {
                System.out.println("\n--- IGRAČ " + (j + 1) + "---");

                System.out.print("Ime: ");
                String ime = sc.nextLine();
                System.out.print("Prezime: ");
                String prezime = sc.nextLine();
                System.out.print("Godina rođenja: ");
                int godinaRodenja = Integer.parseInt(sc.nextLine());
                System.out.print("Broj dresa: ");
                int brojDresa = Integer.parseInt(sc.nextLine());
                System.out.print("Pozicija (1 - VRATAR, 2 - BRANIC, 3 - VEZNJAK, 4 - NAPADAC): ");
                Pozicija[] pozicije = Pozicija.values();
                int brojPozicije;

                do {
                    brojPozicije = Integer.parseInt(sc.nextLine());
                    if (brojPozicije < 1 || brojPozicije > 4) {
                        System.out.println("Pozicija mora biti u rasponu od 1 - 4");
                    }
                    System.out.println();
                } while (brojPozicije < 1 || brojPozicije > 4);

                igraci[j] = new Igrac(ime, prezime, godinaRodenja, brojDresa, pozicije[brojPozicije - 1]);
            }


            System.out.print("Koliko građevina zelite unijeti: ");
            int brojGradevina = Integer.parseInt(sc.nextLine());
            Gradjevina[] gradjevina = new Gradjevina[brojGradevina];
            for (int j = 0; j < brojGradevina; j++) {
                System.out.println("--- GRAĐEVINA " + (j + 1) + " ---\n");
                System.out.print("Vrsta (1 - Stadion, 2 - MedicinskaOrdinacija, 3 - SportskiDom): ");
                int vrstaGradevine;
                while (true) {
                    vrstaGradevine = Integer.parseInt(sc.nextLine());
                    if (vrstaGradevine < 1 || vrstaGradevine > 3) {
                        System.out.println("Upisana gradjevina mora bti od 1 - 3");
                    } else {
                        break;
                    }
                }

                System.out.print("Naziv: ");
                String nazivGradjevine = sc.nextLine();
                System.out.print("Ulica: ");
                String ulicaGradjevine = sc.nextLine();
                System.out.print("Kucni broj: ");
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
                        System.out.print("Natkriven(true/false): ");
                        boolean natkriven = Boolean.parseBoolean(sc.nextLine());
                        gradjevina[j] = new Stadion(adresaGradjevine, nazivGradjevine, kapacitetGradjevine, godinaGodinaGradjevine, natkriven);
                        break;
                    case 2:
                        System.out.print("Broj ambulanti: ");
                        int brojAmbulantiGradjevine = Integer.parseInt(sc.nextLine());
                        System.out.print("Ima hitnu sluzbu(true/false): ");
                        boolean imaHitnuSluzbu = Boolean.parseBoolean(sc.nextLine());
                        gradjevina[j] = new MedicinskaOrdinacija(adresaGradjevine, nazivGradjevine, brojAmbulantiGradjevine, imaHitnuSluzbu);
                        break;
                    case 3:
                        System.out.print("Broj kreveta: ");
                        int brojKrevetaGradjevine = Integer.parseInt(sc.nextLine());
                        System.out.print("Ima blagovaonicu(true/false): ");
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
            System.out.println("==== NOGOMETNI KLUBOVI ====");
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
                System.out.println("Program zavrsava.");
            } else {
                System.out.println("Ovo nije opcija.");
            }
        } while (odabir != 0);


    }
}
