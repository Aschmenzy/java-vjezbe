package hr.tvz.java.tjedan01.pripremalabos1;

public class Osoba {
    private String ime;
    private String prezime;
    private int godinaRodenja;

    public Osoba(String ime, String prezime, int godinaRodenja) {
        this.ime = ime;
        this.prezime = prezime;
        this.godinaRodenja = godinaRodenja;
    }

    //getteri
    public String getIme() {return this.ime;}
    public String getPrezime(){return this.prezime;}
    public int getGodinaRodenja(){return this.godinaRodenja;}

    //setteri
    public void setIme(String ime){this.ime = ime;}
    public void setPrezime(String prezime){this.prezime = prezime;}
    public void setGodinaRodenja(int godinaRodenja) {this.godinaRodenja = godinaRodenja;}
}
