package helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathHelpers {

    public static double truncateDouble(double value){
        int truncatedValue = (int)(value*100);
        //String correctTruncation = String.format("%.2f SEK", newValue);
        double finalValue = (double)truncatedValue / 100.00;
        return finalValue;
    }

    public static double round(double value) {
        double scale = Math.pow(10, 2);
        double doubleM1 = Math.round(value*scale)/scale;
        return doubleM1;
    }


}
