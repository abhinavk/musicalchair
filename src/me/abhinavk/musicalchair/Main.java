package me.abhinavk.musicalchair;

/**
 * Created by Abhinav on 12-12-2014.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        int n=0;

        try {
            n = kbd.nextInt();
            if(n < 2) {
                System.out.println("You cannot play this game with less than 2 players.");
                n = 2;
            }
        } catch (InputMismatchException e) {
            System.out.println("We asked for an integer");
            System.exit(0);
        }


        Person umpire = new Person();

        umpire.setChairs(n - 1);

        Person[] persons = new Person[n];

        for (int i = 0; i < n; i++) {
            persons[i] = new Person(i);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(umpire.getTotalChairs() > 0 ) {
            System.out.println("Round " + (n-umpire.getTotalChairs()));

            for (int i = 0; i < n; i++) {
                if(persons[i].isPlaying() == true)
                    new Thread(persons[i]).start();
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //System.out.println("Number of Threads: " + Thread.activeCount());
            umpire.concludeRound();
            System.out.println();
        }
    }
}
