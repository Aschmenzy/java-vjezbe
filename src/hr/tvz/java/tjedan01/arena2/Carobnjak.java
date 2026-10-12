package hr.tvz.java.tjedan01.arena2;

public class Carobnjak extends Lik {
    private int mana;
    private static final int CIJENA_CAROLIJE = 20;

    public Carobnjak(String ime, int snaga, int mana) {
        super(ime, snaga);
        this.mana = mana;

    }

    public int getMana() {return mana;}

    @Override
    public void napadni(Lik protivnik) {
        if (jeZiv() && mana >= CIJENA_CAROLIJE) {
            int steta = getSnaga() * 2;
            mana -= CIJENA_CAROLIJE;
            System.out.println(getIme() + " baca čaroliju na " + protivnik.getIme()
                    + " za " + steta + " štete!");
            protivnik.primiUdarac(steta);
        } else {
            super.napadni(protivnik);
        }
    }

    @Override
    public String toString() {
        return ("Carobnjak{ime='" + getIme() + "', zdravlje=" + getZdravlje() + ", napad=" + getSnaga() + ", mana= " + mana + "}");
    }
}
