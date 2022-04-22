package Test;

import java.text.DecimalFormat;

public class KeepTwoFloat {
    public static void main(String[] args) {
        float a= (float) 12.121424;

        System.out.println(a);
        a= Float.valueOf(new DecimalFormat("#.00").format(a));
        System.out.println(a);

    }
}
