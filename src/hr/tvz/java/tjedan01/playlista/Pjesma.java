package hr.tvz.java.tjedan01.playlista;

public class Pjesma {
    private final static int ZADANO_TRAJANJE = 180;
    private String naziv;
    private String izvodac;
    private int trajanje;

    private static int brojac = 0;

    public Pjesma(String naziv, String izvodac, int trajanje) {
        this.naziv = naziv;
        this.izvodac = izvodac;
        if (trajanje > 0) {
            this.trajanje = trajanje;
        } else {
            this.trajanje = ZADANO_TRAJANJE;
        }
        brojac++;
    }

    public static int getBrojac() {
        return brojac;
    }

    public static String formatiraj(int sekunde){
        int minute =  sekunde/60;
        int second = sekunde%60;
        return String.format("%02d:%02d", minute, second);
    }

    public boolean jeOdIzvodaca(String izvodac){
        return izvodac.equals(this.izvodac);
    }

    //getteri
    public String getNaziv() {
        return naziv;
    }
    public String getIzvodac() {
        return izvodac;
    }
    public int getTrajanje() {
        return trajanje;
    }

    @Override
    public String toString() {
        return ("Pjesma{naziv='"+naziv+"',izvodac='" + izvodac + "',trajanje=" + formatiraj(trajanje) + "} ");
    }

}
