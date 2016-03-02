package module;

/**
 * Created by Biko on 25.12.2015.
 * for specific debugging
 */
public class Test2 {
    public static void main(String[] args) {
        Calc calc = new Calc(4);
        calc.printTable();
        calc.onKeyPressRightNew();
        System.out.println();
        calc.printTable();

    }
}
