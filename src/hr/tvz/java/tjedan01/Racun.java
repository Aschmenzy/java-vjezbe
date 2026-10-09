package hr.tvz.java.tjedan01;

import java.util.Scanner;

public class Racun {
    public static void main(String[] args) {

        final double GRANICA = 20.0;
        final double POPUST = 0.1;

        Scanner sc = new Scanner(System.in);

        System.out.print("Unesite naziv artikla: ");
        String artikl = sc.nextLine();

        System.out.print("Unesite cijenu: ");
        double cijena = sc.nextDouble();

        System.out.print("Unesite kolicinu: ");
        int kolicina = sc.nextInt();
        sc.close();

        double ukupno = cijena * kolicina;

        System.out.println("------------------------------");
        System.out.printf("Artikl: %s | Cijena: %.2f EUR%n", artikl, cijena);
        System.out.println("Kolicina: " + kolicina);
        System.out.printf("Ukupno: %.2f EUR%n", ukupno);

        if (ukupno > GRANICA) {
            double popust = ukupno * POPUST;
            System.out.printf("Popust: %.2f EUR%n", popust);
            System.out.printf("Za platiti: %.2f EUR%n", ukupno - popust);
        } else {
            System.out.printf("Za platiti: %.2f EUR%n", ukupno);
        }
    }
}