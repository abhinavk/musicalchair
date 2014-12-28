package me.abhinavk.musicalchair;

/**
 * Created by Abhinav on 13-12-2014.
 */

import java.util.ArrayList;

public class Person implements Runnable {
    private int id;
    private boolean playing = false;

    private static int totalChairs = 0;
    private static int chairsOccupied = 0;

    private static ArrayList<Person> players = new ArrayList<Person>();
    //private static ArrayList<Person> tmp_ = new ArrayList<Person>();

    Person(int personid) {
        this.id = personid;
        this.playing = true;
    }

    Person() {

    }

    public void setChairsOccupied(int a) {
        chairsOccupied = a;
    }

    public void setChairs(int a) {
        this.totalChairs = a;
    }

    public int getTotalChairs() {
        return totalChairs;
    }

    public void run() {
        synchronized(players) { // Use any shared object here
            if(this.playing == true && chairsOccupied < totalChairs) {
                System.out.println("Player " + (this.id + 1) +" sat.");
                players.add(this);
                chairsOccupied++;
            } else {
                if(this.playing == true)
                    System.out.println("Player " + (this.id + 1) +" is out.");
                this.playing = false;
                Thread.currentThread().interrupt();
            }
        }
    }

    public void concludeRound() {
        if(totalChairs > 1) {
            System.out.print("On the seats: ");
            for(int i=0;i<players.size();i++)
                System.out.print("Person " + (players.get(i).id + 1) + " ");
            System.out.println();
        } else {
            System.out.println("\nEnd of game\n===========\n");
            System.out.println("Person " + (players.get(0).id + 1) + " is the winner.");
        }

        players.clear();
        totalChairs--;
        chairsOccupied = 0;
    }

    public boolean isPlaying() {
        return playing;
    }

}
