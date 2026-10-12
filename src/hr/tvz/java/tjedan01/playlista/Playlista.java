package hr.tvz.java.tjedan01.playlista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Playlista {
    public static void main(String[] args) {
       Pjesma[] pjesme = new Pjesma[5];

       pjesme[0] = new Pjesma("Kisa nad Savom", "Mali bend", 185);
       pjesme[1] = new Pjesma("Ponoc u Zagrebu", "Nocni Tramvaj", 242);
       pjesme[2] = new Pjesma("Ljeto na Hvaru", "Mali bend", 201);
       pjesme[3] = new Pjesma("Kava i kolac", "Lena K", 158);
       pjesme[4] = new Pjesma("Greska", "Nocni Tramvaj", -30);

        int ukupnoTrajanjeUSekundama = 0;
        for (Pjesma pjesma : pjesme) {
            System.out.println(pjesma);
            ukupnoTrajanjeUSekundama += pjesma.getTrajanje();
        }

        System.out.println("Ukupno trajanje: " + Pjesma.formatiraj(ukupnoTrajanjeUSekundama));
        System.out.println("Broj stvorenih pjesama " + Pjesma.getBrojac());

        Scanner sc = new Scanner(System.in);

        System.out.print("Unesite izvodaca: ");
        String naziv = sc.nextLine();
        sc.close();

        for (Pjesma pjesma : pjesme) {
            if(pjesma.jeOdIzvodaca(naziv)){
                System.out.println(pjesma.getNaziv() + " (" + Pjesma.formatiraj(pjesma.getTrajanje()) + ")");
            }
        }
    }
}
