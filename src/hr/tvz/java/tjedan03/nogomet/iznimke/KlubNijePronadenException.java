package hr.tvz.java.tjedan03.nogomet.iznimke;

public class KlubNijePronadenException extends Exception {
    public KlubNijePronadenException(String nazivKluba) {
        super("Klub '" + nazivKluba + "' nije pronađen.");
    }
}
