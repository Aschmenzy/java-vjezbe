package hr.tvz.java.tjedan02.nogomet.klase;

import hr.tvz.java.tjedan02.nogomet.apstraktne.Osoba;

public class Trener extends Osoba {
    private int godineIskustva;

   public Trener(String ime, String prezime, int godinaRodenja,int godineIskustva) {
        super(ime,prezime,godinaRodenja);
        this.godineIskustva = godineIskustva;
    }

    //getteri
    public int getGodineIskustva(){return this.godineIskustva;}

    //setteri
    public void setGodineIskustva(int godineIskustva){this.godineIskustva = godineIskustva;}

}
