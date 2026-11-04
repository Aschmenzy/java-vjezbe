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

    public int unesiCijeliBroj(String poruka, int min, int max) {
        while (true) {
            System.out.print(poruka);
            try {
                int broj = Integer.parseInt(scanner.nextLine().trim());
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

        while (true) {
            System.out.print(poruka);

            String unos = scanner.nextLine().trim();

            try {
                int odabir = Integer.parseInt(unos);

                if (odabir < 1 || odabir > pozicije.length) {
                    System.out.println("Pogreška: odaberite jednu od ponuđenih vrijednosti.");
                } else {
                    return pozicije[odabir - 1];
                }
            } catch (NumberFormatException e) {
                System.out.println("Pogreška: potrebno je unijeti cijeli broj.");
            }
        }
    }

    public int unesiOdabir(String poruka, int min, int max) {
        while (true) {
            System.out.print(poruka);
            String unos = scanner.nextLine().trim();

            try {
                int odabir = Integer.parseInt(unos);

                if (odabir < min || odabir > max) {
                    System.out.println("Pogreška: odaberite jednu od ponuđenih vrijednosti.");
                } else {
                    return odabir;
                }
            } catch (NumberFormatException e) {
                System.out.println("Pogreška: potrebno je unijeti cijeli broj.");
            }
        }
    }

}
