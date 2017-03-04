package dillonJavaLoopy;
/** *****************************************
 *                                         *
 * NPower Software Development and Testing *
 *            Week 1 Project               *
 *        The Loopy Planet Challenge       *
 *            Language: Java               *
 *            February 7, 2017             *
 *            Dillon Dommer                *
 *                                         *
 ****************************************** */

/* This program prints a list of the planets in the solar system,
 * (excluding Earth), and prompts the user to select a number
 *  that corresponds to a planet via a console-based menu.
 * 
 * It then asks a user to input their weight in pounds, and prints out their
 * weight on the planet that they have chosen.
 * 
 * The program will then repeat the process until 
 * the user enters a number which corresponds to <quit>, which will
 * cause the process to terminate. 
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class DillonJavaLoopyPlanet {

    public static int menu_num;          // Menu number to be input
    public static double weight;         // Weight (lbs) to be input

    public static boolean exit_cond;     // used to exit prompt loops on valid
    // input, this is used for both loops
    public static boolean bigexit_cond;  // used to determine when to exit the
    // "game loop"

    public static String[] planets = {"Jupiter", "Mars", "Mercury", "Neptune",
        "Pluto", "Saturn", "Uranus", "Venus"};

    public static double[] weights = {2.64, 0.38, 0.37, 1.12, 0.04, 1.15,
        1.15, 0.88};

    /*
	 * Contract: printGravity : void -> void
	 * 
	 * Purpose: to calculate and print the gravity (relative to Earth) of the
	 * array of planets
	 * 
	 * Example: printGravity() should produce
	 * "The gravity on Mars is 38% of the gravity on Earth." for each planet
     */
    public static void printGravity() {
        for (int i = 0; i < planets.length; i++) {
            System.out.println("\nThe gravity on " + planets[i] + " is "
                    + weights[i] + "% of the gravity on Earth.");
        }
    }

    /*
	 * Contract: printMenu : void -> void
	 * 
	 * Purpose: to print a text menu of the possible menu selections available
	 * to the user
	 * 
	 * Example: printMenu() should produce "Menu of Planets" \n
	 * "1. Jupiter 2. Mars 3. Mercury" et cetera
     */
    public static void printMenu() {
        System.out.println("           Menu of Planets          ");
        System.out.println("           ==== == =======          ");
        System.out.println("1. Jupiter    2. Mars     3. Mercury");
        System.out.println("4. Neptune    5. Pluto    6. Saturn ");
        System.out.println("7. Uranus     8. Venus    9. <Quit> ");
        System.out.println("10. List the planet gravity factors ");
    }

    /*
	 * Contract: compute_weight : double double -> double
	 * 
	 * Purpose: to calculate an extraterrestrial weight using both an Earth
	 * weight which is 'lbs', and an extraterrestrial 'factor'
	 * 
	 * Example: compute_weight(100.6, 0.45) should produce 45.2
     */
    public static double compute_weight(double lbs, double factor) {
        double lbs_format = lbs * factor;
        return Double.parseDouble(new DecimalFormat("#.#").format(lbs_format));
    }

    /*
	 * Contract: handleMenu : void -> void
	 * 
	 * Purpose: to run the menu loop, which prompts the user for input
	 * 
	 * Example: handleMenu() should produce "Enter your menu choice: "
     */
    public static void handleMenu() {
        do {
            try {
                System.out.print("\nEnter your menu choice: ");
                Scanner scanner = new Scanner(System.in);
                menu_num = Integer.parseInt(scanner.next());

                if (menu_num == 10) // bonus selection: print the list of the different
                // gravity on extraterrestrial planets
                {
                    printGravity();
                    System.out.println();
                    printMenu();
                } // if the input doesn't correspond to a menu selection, we deal
                  // with it via an exception
                else if (menu_num < 1 || menu_num > 9) {
                    System.out.println("\nError! Please enter a number between 1 and 10, inclusive.");
                } else if (menu_num == 9) {
                    System.out.println("\nHave a great day! Goodbye!");
                    exit_cond = true;
                    bigexit_cond = true;
                } else {
                    exit_cond = true; // we have a valid input, don't ask for another
                }
            } catch (NumberFormatException e) // input is NaN
            {
                System.out.println("\nError! Please Enter a valid integer.");
            }

        } while (!exit_cond);
    }

    /*
	 * Contract: handleWeight : void -> void
	 * 
	 * Purpose: to run the loop to get a weight, which prompts the user for
	 * input
	 * 
	 * Example: handleWeight() should produce "Enter your weight on Earth: "
     */
    public static void handleWeight() {
        exit_cond = false;

        if (!bigexit_cond) // if the user wants to exit, this loop will not run
        {
            do {
                try {
                    System.out.print("\nEnter your weight on Earth: ");
                    Scanner scanner = new Scanner(System.in);
                    weight = Double.parseDouble(scanner.next());

                    if (weight < 0) {           // handles a negative weight
                        System.out.println("\nError! Please enter a positive weight.");                                   
                    } else 
                    {
                        exit_cond = true; // we have a valid input, don't ask for another
                    }
                } catch (NumberFormatException e) // input is NaN
                {
                    System.out.println("\nError! Please enter a a valid number.");
                }

            } while (!exit_cond);

            exit_cond = false; // this allows handleMenu to run again

            System.out.println();

            double format_weight = Double.parseDouble(new DecimalFormat("#.#")
                    .format(weight));

            System.out.println("Your weight of " + format_weight
                    + " pounds on Earth would be "
                    + compute_weight(weight, weights[menu_num - 1])
                    + " pounds on " + planets[menu_num - 1] + "."); // print the
            // result
            System.out.println();
        }
    }

    public static void main(String[] args) {
        while (!bigexit_cond) // "game loop", can be terminated in the
        // printMenu() method
        {
            printMenu();      // print the menu
            handleMenu();     // handle first input
            handleWeight();   // handle second input
        }
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // keeps the console open after the program
        // terminates in order to read goodbye message
    }
}
