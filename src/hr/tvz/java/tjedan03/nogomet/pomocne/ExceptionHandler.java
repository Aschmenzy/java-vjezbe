package hr.tvz.java.tjedan03.nogomet.pomocne;

import hr.tvz.java.tjedan03.nogomet.enumeracije.Pozicija;

import java.util.Scanner;

public class ExceptionHandler {
    private final Scanner scanner;

    public ExceptionHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public String unesiObavezanTekst(String poruka) {
        while (true) {
            System.out.print(poruka);
            String tekst = scanner.nextLine().trim();

            if (tekst.isEmpty()) System.out.println("Pogreška: tekst ne smije biti prazan.");
            else return tekst;

        }
    }

    //pomocne metode(privatne)
    private int unesiBrojURasponu(String poruka, int min, int max, String greska) {
        while (true) {
            System.out.print(poruka);
            try {
                int broj = Integer.parseInt(scanner.nextLine().trim());
                if (broj < min || broj > max) {
                    throw new IllegalArgumentException(greska);
                }
                return broj;
            } catch (NumberFormatException e) {
                System.out.println("Pogreška: potrebno je unijeti cijeli broj.");
            } catch (IllegalArgumentException e) {
                System.out.println("Pogreška: " + e.getMessage());
            }
        }
    }

    public int unesiCijeliBroj(String poruka, int min, int max) {
        return unesiBrojURasponu(poruka, min, max, "vrijednost nije u dopuštenom rasponu.");
    }

    public Integer unesiOpcionalniCijeliBroj(String poruka, int min, int max) {
        while (true) {
            System.out.print(poruka);
            String unos = scanner.nextLine().trim();

            if (unos.isEmpty()) return null;

            try {
                Integer broj = Integer.parseInt(unos);

                if (broj < min || broj > max) {
                    throw new IllegalArgumentException("vrijednost nije u dopuštenom rasponu.");
                }
                return broj;
            } catch (NumberFormatException e) {
                System.out.println("Pogreška: potrebno je unijeti cijeli broj.");
            } catch (IllegalArgumentException e) {
                System.out.println("Pogreška: " + e.getMessage());
            }
        }
    }

    public boolean unesiBoolean(String poruka) {
        while (true) {
            System.out.print(poruka);
            String unos = scanner.nextLine().trim();

            if (unos.equalsIgnoreCase("true")) return true;
            else if (unos.equalsIgnoreCase("false")) return false;
            else System.out.println("Pogreška: unesite true ili false.");

        }
    }

    public Boolean unesiOpcionalniBoolean(String poruka) {
        while (true) {
            System.out.print(poruka);
            String unos = scanner.nextLine().trim();
            if (unos.isEmpty()) return null;

            if (unos.equalsIgnoreCase("true")) return true;
            else if (unos.equalsIgnoreCase("false")) return false;
            else System.out.println("Pogreška: unesite true ili false.");

        }
    }

    public Pozicija unesiPoziciju(String poruka) {
        Pozicija[] pozicije = Pozicija.values();
        int odabir = unesiOdabir(poruka, 1, pozicije.length);
        return pozicije[odabir - 1];
    }

    public int unesiOdabir(String poruka, int min, int max) {
        return unesiBrojURasponu(poruka,min,max,"odaberite jednu od ponuđenih vrijednosti.");
    }
}
