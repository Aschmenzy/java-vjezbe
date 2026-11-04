package hr.tvz.java.tjedan03.nogomet.aplikacija;

import hr.tvz.java.tjedan03.nogomet.apstraktne.Gradjevina;
import hr.tvz.java.tjedan03.nogomet.iznimke.KlubNijePronadenException;
import hr.tvz.java.tjedan03.nogomet.klase.*;
import hr.tvz.java.tjedan03.nogomet.zapisi.Adresa;
import hr.tvz.java.tjedan03.nogomet.pomocne.ExceptionHandler;

import java.time.Year;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final int TRENUTNA_GODINA = Year.now().getValue();
        final int MAX = Integer.MAX_VALUE;
        Scanner sc = new Scanner(System.in);

        try {
            ExceptionHandler handler = new ExceptionHandler(sc);

            int brojKlubova = handler.unesiCijeliBroj("Koliko klubova želite unijeti: ", 1, MAX);
            NogometniKlub[] klubovi = new NogometniKlub[brojKlubova];

            for (int i = 0; i < brojKlubova; i++) {
                System.out.println("\n===== UNOS KLUBA " + (i + 1) + " =====");

                String naziv = handler.unesiObavezanTekst("Naziv kluba: ");
                String grad = handler.unesiObavezanTekst("Grad: ");
                int godinaOsnivanja = handler.unesiCijeliBroj("Godina osnivanja: ", 1, TRENUTNA_GODINA);
                System.out.println();

                //trener
                String imeTrenera = handler.unesiObavezanTekst("Ime trenera: ");
                String prezimeTrenera = handler.unesiObavezanTekst("Prezime trenera: ");
                int godinaRodenja = handler.unesiCijeliBroj("Godina rođenja: ", 1, TRENUTNA_GODINA);
                int godineIskustva = handler.unesiCijeliBroj("Godine iskustva: ", 0, TRENUTNA_GODINA - godinaRodenja);
                Trener privremeniTrener = new Trener(imeTrenera, prezimeTrenera, godinaRodenja, godineIskustva);

                //upis igraca
                int brojIgraca = handler.unesiCijeliBroj("\nKoliko igrača želite unijeti: ", 0, MAX);
                Igrac[] igraci = new Igrac[brojIgraca];

                for (int j = 0; j < brojIgraca; j++) {
                    System.out.println("\n--- IGRAČ " + (j + 1) + " ---");

                    igraci[j] = new Igrac(
                            handler.unesiObavezanTekst("Ime: "),
                            handler.unesiObavezanTekst("Prezime: "),
                            handler.unesiCijeliBroj("Godina rođenja: ", 1, TRENUTNA_GODINA),
                            handler.unesiCijeliBroj("Broj dresa: ", 1, 99),
                            handler.unesiPoziciju("Pozicija (1 - VRATAR, 2 - BRANIC, 3 - VEZNJAK, 4 - NAPADAC): "));
                }


                int brojGradevina = handler.unesiCijeliBroj("\nKoliko građevina želite unijeti: ", 0, MAX);
                Gradjevina[] gradjevina = new Gradjevina[brojGradevina];
                for (int j = 0; j < brojGradevina; j++) {
                    System.out.println("\n--- GRAĐEVINA " + (j + 1) + " ---");


                    int vrstaGradevine = handler.unesiOdabir("Vrsta (1 - Stadion, 2 - MedicinskaOrdinacija, 3 - SportskiDom): ", 1, 3);


                    String nazivGradjevine = handler.unesiObavezanTekst("Naziv: ");

                    Adresa adresaGradjevine = new Adresa(
                            handler.unesiObavezanTekst("Ulica: "),
                            handler.unesiObavezanTekst("Kućni broj: "),
                            handler.unesiObavezanTekst("Grad: "));

                    switch (vrstaGradevine) {
                        case 1:
                            gradjevina[j] = new Stadion(
                                    adresaGradjevine,
                                    nazivGradjevine,
                                    handler.unesiOpcionalniCijeliBroj("Kapacitet: ", 1, MAX),
                                    handler.unesiOpcionalniCijeliBroj("Godina izgradnje: ", 1, TRENUTNA_GODINA),
                                    handler.unesiOpcionalniBoolean("Natkriven (true/false): ")
                            );
                            break;
                        case 2:
                            gradjevina[j] = new MedicinskaOrdinacija(
                                    adresaGradjevine,
                                    nazivGradjevine,
                                    handler.unesiCijeliBroj("Broj ambulanti: ", 1, MAX),
                                    handler.unesiBoolean("Ima hitnu službu (true/false): "));
                            break;
                        case 3:
                            gradjevina[j] = new SportskiDom(
                                    adresaGradjevine,
                                    nazivGradjevine,
                                    handler.unesiCijeliBroj("Broj kreveta: ", 1, MAX),
                                    handler.unesiBoolean("Ima blagovaonicu (true/false): "));
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
                odabir = handler.unesiOdabir("Odabir: ", 0, 1);

                if (odabir == 1) {
                    try {
                       NogometniKlub pronadeni = pronadiKlub(klubovi,
                               handler.unesiObavezanTekst("Unesite naziv kluba: "));
                        System.out.println(pronadeni.getOpis());
                    } catch (KlubNijePronadenException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } while (odabir != 0);
        } finally {
            sc.close();
            System.out.println("Program je završio.");
        }


    }

    private static NogometniKlub pronadiKlub(NogometniKlub[] klubovi, String naziv) throws KlubNijePronadenException {
        for (NogometniKlub nk : klubovi) {
            if (nk.getNaziv().equalsIgnoreCase(naziv)){
                return nk;
            }
        }
        throw new KlubNijePronadenException(naziv);
    }
}


