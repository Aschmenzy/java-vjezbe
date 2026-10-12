package hr.tvz.java.tjedan01.pripremalabos1;

import java.util.Scanner;

public class Glavna {
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

            //ucitavanje stadiona
            System.out.print("Naziv stadiona: ");
            String nazivStadiona = sc.nextLine();
            System.out.print("Grad stadiona: ");
            String gradStadiona = sc.nextLine();
            System.out.print("Kapacitet: ");
            int kapacitet = Integer.parseInt(sc.nextLine());
            System.out.print("Godina izgradnje: ");
            int godinaIzgradnje = Integer.parseInt(sc.nextLine());
            System.out.print("Je li stadion natkriven: ");
            boolean natkriven = Boolean.parseBoolean(sc.nextLine());

            Stadion privremeniStadion = new Stadion(nazivStadiona, gradStadiona);
            privremeniStadion.setKapacitet(kapacitet);
            privremeniStadion.setGodinaIzgradnje(godinaIzgradnje);
            privremeniStadion.setNatkriven(natkriven);

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
                System.out.print("Pozicija: ");
                String pozicija = sc.nextLine();

                Igrac igr = new Igrac(ime, prezime, godinaRodenja, brojDresa, pozicija);
                igraci[j] = igr;
            }

            NogometniKlub nk = new NogometniKlub(naziv, grad, godinaOsnivanja, privremeniStadion, privremeniTrener, igraci);
            klubovi[i] = nk;
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
            }else if (odabir == 0) {
                System.out.println("Program zavrsava.");
            }else{
                System.out.println("Ovo nije opcija.");
            }
        } while (odabir != 0 );


    }
}
