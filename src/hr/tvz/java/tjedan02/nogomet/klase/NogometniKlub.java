package hr.tvz.java.tjedan02.nogomet.klase;

import hr.tvz.java.tjedan02.nogomet.apstraktne.Gradjevina;
import hr.tvz.java.tjedan02.nogomet.sucelja.Opisivo;

public class NogometniKlub implements Opisivo {
    private String naziv;
    private String grad;
    private int godinaOsnivanja;
    private Gradjevina[] gradjevine;
    private Trener trener;
    private Igrac[] igraci;

    public NogometniKlub(String naziv, String grad, int godinaOsnivanja, Gradjevina[] gradjevina, Trener trener, Igrac[] igraci) {
        this.naziv = naziv;
        this.grad = grad;
        this.godinaOsnivanja = godinaOsnivanja;
        this.gradjevine = gradjevina;
        this.trener = trener;
        this.igraci = igraci;
    }


    //getteri
    public String getNaziv() {
        return this.naziv;
    }

    public String getGrad() {
        return this.grad;
    }

    public int getGodinaOsnivanja() {
        return this.godinaOsnivanja;
    }

    public Gradjevina[] getGradjevine() {
        return gradjevine;
    }

    public Trener getTrener() {
        return this.trener;
    }

    public Igrac[] getIgraci() {
        return this.igraci;
    }

    @Override
    public String getOpis() {
        String opis = "===== " + naziv.toUpperCase() + " =====\n";
        opis += "Grad: " + grad + "\n";
        opis += "Godina osnivanja: " + godinaOsnivanja + "\n";
        opis += "Trener: " + trener.getIme() + " " + trener.getPrezime() + "\n";

        opis += "\nIgrači:\n";
        for (int i = 0; i < igraci.length; i++) {
            Igrac ig = igraci[i];
            opis += (i + 1) + ". " + ig.getIme() + " " + ig.getPrezime() + ", "
                    + ig.getGodinaRodenja() + "., dres " + ig.getBrojDresa() + ", " + ig.getPozicija() + "\n";
        }

        opis += "\nGrađevine:\n";
        for (int i = 0; i < gradjevine.length; i++) {
            Gradjevina g = gradjevine[i];
            String vrsta = "";
            String posebno = "";

            if (g instanceof Stadion s) {
                vrsta = "Stadion";
                posebno = "   Godina izgradnje: " + s.getGodinaIzgradnje() + "\n"
                        + "   Natkriven: " + s.getNatkriven() + "\n";
            } else if (g instanceof MedicinskaOrdinacija m) {
                vrsta = "Medicinska ordinacija";
                posebno = "   Ima hitnu službu: " + m.isImaHitnuSluzbu() + "\n";
            } else if (g instanceof SportskiDom d) {
                vrsta = "Sportski dom";
                posebno = "   Ima blagovaonicu: " + d.isImaBlagovaonicu() + "\n";
            }

            opis += "\n" + (i + 1) + ". " + vrsta + ": " + g.getNaziv() + "\n";
            opis += "   Adresa: " + g.getAdresa().ulica() + " " + g.getAdresa().kucniBroj()
                    + ", " + g.getAdresa().grad() + "\n";
            opis += "   " + g.getKapacitetZaIspis() + "\n";
            opis += posebno;
        }

        opis += "\nUkupan broj kreveta: " + getUkupanBrojKreveta() + "\n";
        return opis;
    }

    public int getUkupanBrojKreveta(){
        int kreveta = 0;
        for (Gradjevina g : gradjevine) {
           if (g instanceof SportskiDom s) kreveta += s.getBrojKreveta();
        }
        return kreveta;
    }

    //setteri
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public void setGodinaOsnivanja(int godinaOsnivanja) {
        this.godinaOsnivanja = godinaOsnivanja;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public void setIgraci(Igrac[] igraci) {
        this.igraci = igraci;
    }

    public void setGradjevine(Gradjevina[] gradjevine) {
        this.gradjevine = gradjevine;
    }


}
