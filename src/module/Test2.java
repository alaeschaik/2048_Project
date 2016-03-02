package module;

import java.io.IOException;

/**
 * Created by Biko on 25.12.2015.
 * for specific debugging
 */
public class Test2 {
    public static void main(String[] args) {
        Calc calc = new Calc(4);
        System.out.println(calc.toString());
        calc.setTableSize(6);
        try {
            calc.saveStatus();
            calc.readStatus();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println(calc.toString());

    }
}
