package module;

import java.util.Scanner;

/**
 * Created by Biko on 23.12.2015.
 */
public class Test {
    private static Scanner scanner;

    public static void main(String[] args) {
        System.out.println("---------------------------- Test 2048 ----------------------------");
        Calc calc = new Calc(4);
        calc.initializeValue();

        int choice = 0;
        scanner = new Scanner(System.in);
        while (choice != 1) {
            calc.printTable();
            System.out.println("8. UP");
            System.out.println("2. DOWN");
            System.out.println("4. LEFT");
            System.out.println("6. RIGHT");
            System.out.println("1. EXIT");

            if (scanner != null)
                choice = scanner.nextInt();

            System.out.println();

            if (choice <= 8) {
                switch (choice) {
                    case 1:
                        System.out.println("Game exit");
                        System.exit(0);
                        break;
                    case 2:
                        //calc.onKeyPressDown(); break;
                    case 4:
                        calc.onKeyPressLeft();
                        break;
                    case 6:
                        calc.onKeyPressRight();
                        break;
                    case 8:
                        //calc.onKeyPressUp(); break;
                }
            }
        }
        System.out.println("-------------------------------------------------------------------");
    }
}
