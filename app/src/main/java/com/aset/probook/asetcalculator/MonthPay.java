package com.aset.probook.asetcalculator;

import android.os.Debug;
import android.util.Log;

import java.text.DecimalFormat;

public class MonthPay {
        int month;
        double coef = 0;
        double coef2 = 0;
        double percent1;
        double percent2;
        double result;
        private double limit = 519;
        double e;
        public MonthPay( int month, int salary, int credit, boolean check ){
            this.month = month;
            DecimalFormat df = new DecimalFormat("#######");
            // if phone is checked then these percentages
            if ( check )
                 // в случае изменений в процентаже для моблильных телефонов, менайте эти проценты
            {
                percent1 = 0.4;
                percent2 = 0.35;
            }
            // if other is checked then these percentages
            else if ( !check )
            {
                // в случае изменений в процентаже для остальной техники, менайте эти проценты
                percent1 = 0.4;
                percent2 = 0.35;
            }

            // Calculating DTI
            if ( salary > 519 ) {
                e = (salary * 0.5) - credit;
            }
            else {
                e = (salary * 0.4) - credit;
            }

            // Calculating 2 different coeficients
            if ( month <= 12 ) {
                coef = (percent1 / 12) / (1 - (Math.pow((1 + percent1 / 12), (-1 * month))));
            }
            coef2 = (percent2 / 12) / (1 - (Math.pow((1 + percent2 / 12), (-1 * month))));
            // comparing which percentage is higher to assign it to the "coef" variable

            if ( e / coef >= 499.99 )
            {
                coef = coef2;
            }

            // choosing case for the final result
            if ( e > 0 ) {
                result = e / coef;
                result = Double.valueOf(df.format(result));
                if (result > 4000) {
                    result = 4000;
                } else if (result < 200) {
                    result = 0;
                } else if (result < 500 && month > 12) {
                    result = 0;
                }
            }
            else {
                result = 0;
            }
            //            if (salary >= 200 && salary <= 499.99) {
//                coef = (0.4 / 12) / (1 - (Math.pow((1 + 0.4 / 12), (-1 * month))));
//            }
//            if (salary >= 500 && salary <= 4000) {
//                coef = (0.35 / 12) / (1 - (Math.pow((1 + 0.35 / 12), (-1 * month))));
//            }
//            else
        }

    public double getResult() {
        return result;
    }

    public int getMonth() {
        return month;
    }
}

