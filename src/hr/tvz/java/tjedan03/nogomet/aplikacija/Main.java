package hr.tvz.java.tjedan03.nogomet.aplikacija;

import hr.tvz.java.tjedan03.nogomet.apstraktne.Gradjevina;
import hr.tvz.java.tjedan03.nogomet.iznimke.KlubNijePronadenException;
import hr.tvz.java.tjedan03.nogomet.klase.*;
import hr.tvz.java.tjedan03.nogomet.zapisi.Adresa;
import hr.tvz.java.tjedan03.nogomet.pomocne.ExceptionHandler;

import java.time.Year;
import java.util.Scanner;
import java.util.logging.Handler;

public class Main {
    private static final int TRENUTNA_GODINA = Year.now().getValue();
    private static final int BEZ_GORNJE_GRANICE = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            ExceptionHandler handler = new ExceptionHandler(sc);

            int brojKlubova = handler.unesiCijeliBroj("Koliko klubova želite unijeti: ", 1, BEZ_GORNJE_GRANICE);
            NogometniKlub[] klubovi = new NogometniKlub[brojKlubova];

            for (int i = 0; i < brojKlubova; i++) {
                System.out.println("\n===== UNOS KLUBA " + (i + 1) + " =====");

                String naziv = handler.unesiObavezanTekst("Naziv kluba: ");
                String grad = handler.unesiObavezanTekst("Grad: ");
                int godinaOsnivanja = handler.unesiCijeliBroj("Godina osnivanja: ", 1, TRENUTNA_GODINA);
                System.out.println();

                Trener trener = unesiTrenera(handler);

                Igrac[] igraci = unesiViseIgraca(handler);

                Gradjevina[] gradjevine = unesiViseGradjevina(handler);

                klubovi[i] = new NogometniKlub(naziv, grad, godinaOsnivanja, gradjevine, trener, igraci);
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

    //pomocne metode u mainu
    private static NogometniKlub pronadiKlub(NogometniKlub[] klubovi, String naziv) throws KlubNijePronadenException {
        for (NogometniKlub nk : klubovi) {
            if (nk.getNaziv().equalsIgnoreCase(naziv)) {
                return nk;
            }
        }
        throw new KlubNijePronadenException(naziv);
    }

    private static Trener unesiTrenera(ExceptionHandler handler) {
        String imeTrenera = handler.unesiObavezanTekst("Ime trenera: ");
        String prezimeTrenera = handler.unesiObavezanTekst("Prezime trenera: ");
        int godinaRodenja = handler.unesiCijeliBroj("Godina rođenja: ", 1, TRENUTNA_GODINA);
        int godineIskustva = handler.unesiCijeliBroj("Godine iskustva: ", 0, TRENUTNA_GODINA - godinaRodenja);

        return new Trener(imeTrenera, prezimeTrenera, godinaRodenja, godineIskustva);
    }

    private static Igrac unesiJednogIgraca(ExceptionHandler handler) {
        return new Igrac(
                handler.unesiObavezanTekst("Ime: "),
                handler.unesiObavezanTekst("Prezime: "),
                handler.unesiCijeliBroj("Godina rođenja: ", 1, TRENUTNA_GODINA),
                handler.unesiCijeliBroj("Broj dresa: ", 1, 99),
                handler.unesiPoziciju("Pozicija (1 - VRATAR, 2 - BRANIC, 3 - VEZNJAK, 4 - NAPADAC): "));
    }

    private static Igrac[] unesiViseIgraca(ExceptionHandler handler) {
        int brojIgraca = handler.unesiCijeliBroj("\nKoliko igrača želite unijeti: ", 0, BEZ_GORNJE_GRANICE);
        Igrac[] igraci = new Igrac[brojIgraca];

        for (int i = 0; i < brojIgraca; i++) {
            System.out.println("\n--- IGRAČ " + (i + 1) + " ---");
            igraci[i] = unesiJednogIgraca(handler);
        }
        return igraci;
    }

    private static Gradjevina unesiJednuGradjevinu(ExceptionHandler handler) {
        int vrstaGradevine = handler.unesiOdabir("Vrsta (1 - Stadion, 2 - MedicinskaOrdinacija, 3 - SportskiDom): ", 1, 3);
        String nazivGradjevine = handler.unesiObavezanTekst("Naziv: ");
        Adresa adresaGradjevine = new Adresa(
                handler.unesiObavezanTekst("Ulica: "),
                handler.unesiObavezanTekst("Kućni broj: "),
                handler.unesiObavezanTekst("Grad: "));

        return switch (vrstaGradevine) {
            case 1 -> new Stadion(
                    adresaGradjevine,
                    nazivGradjevine,
                    handler.unesiOpcionalniCijeliBroj("Kapacitet: ", 1, BEZ_GORNJE_GRANICE),
                    handler.unesiOpcionalniCijeliBroj("Godina izgradnje: ", 1, TRENUTNA_GODINA),
                    handler.unesiOpcionalniBoolean("Natkriven (true/false): "));
            case 2 -> new MedicinskaOrdinacija(
                    adresaGradjevine,
                    nazivGradjevine,
                    handler.unesiCijeliBroj("Broj ambulanti: ", 1, BEZ_GORNJE_GRANICE),
                    handler.unesiBoolean("Ima hitnu službu (true/false): "));
            case 3 -> new SportskiDom(
                    adresaGradjevine,
                    nazivGradjevine,
                    handler.unesiCijeliBroj("Broj kreveta: ", 1, BEZ_GORNJE_GRANICE),
                    handler.unesiBoolean("Ima blagovaonicu (true/false): "));
            default -> throw new IllegalStateException("Nepoznata vrsta građevine: " + vrstaGradevine);
        };
    }

    private static Gradjevina[] unesiViseGradjevina(ExceptionHandler handler) {
        int brojGradevina = handler.unesiCijeliBroj("\nKoliko građevina želite unijeti: ", 0, BEZ_GORNJE_GRANICE);
        Gradjevina[] gradjevine = new Gradjevina[brojGradevina];
        for (int i = 0; i < brojGradevina; i++) {
            System.out.println("\n--- GRAĐEVINA " + (i + 1) + " ---");

            gradjevine[i] = unesiJednuGradjevinu(handler);
        }
        return gradjevine;
    }


}


