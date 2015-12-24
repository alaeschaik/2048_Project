package module;

/**
 * Created by Biko on 23.12.2015.
 */
public class Test {


    public static void main(String[] args) {
        Calc calc= new Calc(4);
        calc.printTable();
        System.out.println();

        calc.onKeyPressLeftNew();
        calc.printTable();
        calc.onKeyPressLeftNew();
        calc.printTable();



    }
}
