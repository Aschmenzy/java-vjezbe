package hr.tvz.java.tjedan01.pripremalabos1;

public class Igrac extends Osoba {
    private int brojDresa;
    private String pozicija;

    //konstante
    public static final String POZICIJA_VRATA = "VRATAR";
    public static final String POZICIJA_BRANIC = "BRANIC";
    public static final String POZICIJA_VEZNJAK = "VEZNJAK";
    public static final String POZICIJA_NAPADAC = "NAPADAC";

    public Igrac(String ime, String prezime, int godinaRodenja, int brojDresa, String pozicija) {
        super(ime, prezime, godinaRodenja);
        this.brojDresa = brojDresa;
        this.pozicija = pozicija;
    }

    //getteri
    public int getBrojDresa() {
        return brojDresa;
    }

    public String getPozicija() {
        return pozicija;
    }

    //setteri
    public void setBrojDresa(int brojDresa) {
        this.brojDresa = brojDresa;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }
}
