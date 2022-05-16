package Chimera.tech;

import model.Database;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Database database = new Database();


        database.open();

        Scanner scanner = new Scanner(System.in);

            int quit = 0;
            System.out.println("Welcome to the Fortnite Database by Aldibah Dev on Github! This is a test command line" +
                    "application, as I am working on more projects for this database file in the future!");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("This database contains the various ways certain media is connected to Fortnite. ");
            System.out.println("This database was made with the image made by @Snowfoot_ on Twitter");
            System.out.println("--------------------------------------------------------------------------------");

            while(quit==0) {
                System.out.println("Enter a number for what you would like the application to do. ");
                System.out.println("1: Query all items in the database");
                System.out.println("2: Query all connections for an item");
                System.out.println("3: Quit the application. ");

                int decision = scanner.nextInt();

                switch (decision) {
                    case (1):
                        database.queryGames();
                        break;
                    case (2):
                        System.out.println("Please enter the ID of the item you want to query.");
                        System.out.println("If you need a reminder of what the ID of your item is, use the first option.");
                        int id = scanner.nextInt();
                        database.queryConnection(id);
                        break;
                    case (3):
                        quit = 1;
                }
            }




    }
}
