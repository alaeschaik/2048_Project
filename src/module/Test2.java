package module;

/**
 * Created by Biko on 25.12.2015.
 * for specific debugging
 */
public class Test2 {
    public static void main(String[] args) {
        Calc calc = new Calc(6);
        calc.printTable();
        System.out.println();
        calc.onKeyPressRightNew();
        calc.printTable();

    }

}
