package com.corobceanu;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {

    private static final BigDecimal TWELVE_HUNDRED = BigDecimal.valueOf(1200);
    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);
    private static final RoundingMode ROUNDING_MODE = RoundingMode.UP;
    private static final int SCALE = 14;

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Need 3 arguments: investor balance amount, interest rate, and monthly payment amount");
            return;

        }

        BigDecimal balanceAmt = convertStringToBigDecimal(args[0]);
        BigDecimal interestRate = convertStringToBigDecimal(args[1]);
        BigDecimal pipAmount = convertStringToBigDecimal(args[2]);

        Integer rmm = calculateRemainingMaturityTermLog(balanceAmt, interestRate, pipAmount);
        System.out.println(String.format("For balance amount %f, interest rate %f, and monthly payment %f, remaining maturity term is %d months",
                balanceAmt, interestRate, pipAmount, rmm));
    }

    private static BigDecimal convertStringToBigDecimal(String arg) {
        try {
            return new BigDecimal(arg);
        } catch (NumberFormatException nfe) {
            System.err.println(String.format("Argument %s is invalid format for decimal", arg));
            throw nfe;
        }
    }

    private static int calculateRemainingMaturityTermLog(BigDecimal balanceAmt, BigDecimal interestRate, BigDecimal pipAmount) {
        int finalValue;
        try {
            BigDecimal step1 = interestRate.divide(TWELVE_HUNDRED, SCALE, ROUNDING_MODE);
            BigDecimal step2 = step1.divide(pipAmount, SCALE, ROUNDING_MODE);
            BigDecimal step3 = balanceAmt.multiply(step2);
            BigDecimal step4 = BigDecimal.ONE.subtract(step3);
            BigDecimal step5 = BigDecimal.valueOf(Math.log10(step4.doubleValue())).negate();
            BigDecimal step6 = BigDecimal.ONE.add(interestRate.divide(TWELVE_HUNDRED, SCALE, ROUNDING_MODE));
            BigDecimal step7 = BigDecimal.valueOf(Math.log10(step6.doubleValue()));
            BigDecimal step8 = step5.divide(step7, 0, ROUNDING_MODE);
            return step8.intValue();

        } catch (NumberFormatException nfe) {
            System.err.println("Invalid combination of loan balance amount, interest rate, and monthly payment amount caused an error.");
            throw nfe;
        }
    }
}
