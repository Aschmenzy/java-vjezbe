package hr.tvz.java.tjedan02.nogomet.klase;

import hr.tvz.java.tjedan02.nogomet.apstraktne.Gradjevina;
import hr.tvz.java.tjedan02.nogomet.zapisi.Adresa;

public class MedicinskaOrdinacija extends Gradjevina {
    private int brojAmbulanti;
    private boolean imaHitnuSluzbu;

    public MedicinskaOrdinacija(Adresa adresa, String naziv, int brojAmbulanti, boolean imaHitnuSluzbu) {
        super(adresa, naziv);
        this.brojAmbulanti = brojAmbulanti;
        this.imaHitnuSluzbu = imaHitnuSluzbu;
    }


    @Override
    public String getKapacitetZaIspis() {
        return "Broj ambulanti: " + brojAmbulanti;
    }


    public int getBrojAmbulanti() {
        return brojAmbulanti;
    }

    public void setBrojAmbulanti(int brojAmbulanti) {
        this.brojAmbulanti = brojAmbulanti;
    }

    public boolean isImaHitnuSluzbu() {
        return imaHitnuSluzbu;
    }

    public void setImaHitnuSluzbu(boolean imaHitnuSluzbu) {
        this.imaHitnuSluzbu = imaHitnuSluzbu;
    }


}
