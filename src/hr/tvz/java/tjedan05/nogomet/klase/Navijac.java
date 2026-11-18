package hr.tvz.java.tjedan05.nogomet.klase;


import hr.tvz.java.tjedan05.nogomet.apstraktne.Osoba;

public class Navijac extends Osoba {
    private int brojPosjecenihUtakmica;
    private int glasnocaNavijanja;

    public Navijac(String ime, String prezime, int godinaRodenja, int brojPosjecenihUtakmica, int glasnocaNavijanja) {
        super(ime, prezime, godinaRodenja);
        this.glasnocaNavijanja = glasnocaNavijanja;
        this.brojPosjecenihUtakmica = brojPosjecenihUtakmica;
    }

    public int getBrojPosjecenihUtakmica() {
        return brojPosjecenihUtakmica;
    }

    public void setBrojPosjecenihUtakmica(int brojPosjecenihUtakmica) {
        this.brojPosjecenihUtakmica = brojPosjecenihUtakmica;
    }

    public int getGlasnocaNavijanja() {
        return glasnocaNavijanja;
    }

    public void setGlasnocaNavijanja(int glasnocaNavijanja) {
        this.glasnocaNavijanja = glasnocaNavijanja;
    }
}
