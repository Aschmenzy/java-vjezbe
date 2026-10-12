package hr.tvz.java.tjedan01.arena2;

public class Lik {
    private static final int POCETNO_ZDRAVLJE = 100;
    private String ime;
    private int zdravlje;
    private int snaga;


    public Lik(String ime, int snaga) {
        this.ime = ime;
        this.zdravlje = POCETNO_ZDRAVLJE;
        this.snaga = snaga;
    }

    @Override
    public String toString() {
        return ("Lik{ime='" + ime + "', zdravlje=" + zdravlje + ", napad=" + snaga + "}");
    }

    //getteri za privatne atribute
    public String getIme() {
        return this.ime;
    }

    public int getZdravlje() {
        return this.zdravlje;
    }

    public int getSnaga() {
        return this.snaga;
    }

    //setteri za privatne atribute
    public void setSnaga(int snaga) {
        this.snaga = snaga;
    }

    public void primiUdarac(int steta) {
        if(steta < 0) { return; }

        if (zdravlje -  steta > 0) {
            zdravlje -= steta;
        } else zdravlje = 0;
    }

    public void napadni(Lik protivnik) {
        if (this.jeZiv()) {
            System.out.println(this.ime + " napada " + protivnik.getIme() + " za " + this.snaga + " stete.");
            protivnik.primiUdarac(this.getSnaga());
        } else {
            System.out.println( this.ime + " ne moze napasti jer je mrtav");
        }
    }

    public boolean jeZiv() {
        return zdravlje > 0;
    }
}
