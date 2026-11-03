package hr.tvz.java.tjedan03.nogomet.klase;

import hr.tvz.java.tjedan03.nogomet.apstraktne.Gradjevina;
import hr.tvz.java.tjedan03.nogomet.zapisi.Adresa;

public class SportskiDom extends Gradjevina {
    private int brojKreveta;
    private boolean imaBlagovaonicu;


    public SportskiDom(Adresa adresa, String naziv, int brojKreveta, boolean imaBlagovaonicu) {
        super(adresa, naziv);
        this.brojKreveta = brojKreveta;
        this.imaBlagovaonicu = imaBlagovaonicu;
    }

    @Override
    public String getKapacitetZaIspis() {
        return "Broj kreveta: " + brojKreveta;
    }

    @Override
    public String getVrsta() {
        return "Sportski dom";
    }

    @Override
    public String getPosebniPodaci() {
        return "   Ima blagovaonicu: " + imaBlagovaonicu + "\n";
    }

    public int getBrojKreveta() {
        return brojKreveta;
    }

    public void setBrojKreveta(int brojKreveta) {
        this.brojKreveta = brojKreveta;
    }

    public boolean isImaBlagovaonicu() {
        return imaBlagovaonicu;
    }

    public void setImaBlagovaonicu(boolean imaBlagovaonicu) {
        this.imaBlagovaonicu = imaBlagovaonicu;
    }
}
