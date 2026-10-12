package hr.tvz.java.tjedan01.arena2;

public class Ratnik extends Lik {
    private int oklop;

    public Ratnik(String ime, int snaga, int oklop) {
        super(ime, snaga);
        this.oklop = oklop;
    }


    @Override
    public void primiUdarac(int steta) {
        if (steta <= 0) {
            return;
        } else if (steta - oklop > 0) {
            super.primiUdarac(steta - oklop);
        } else {
            super.primiUdarac(1);
        }

    }

    @Override
    public String toString() {
        return ("Ratnik{ime='" + getIme() + "', zdravlje=" + getZdravlje() + ", napad=" + getSnaga() + ", oklop= " + oklop + "}");
    }
}
