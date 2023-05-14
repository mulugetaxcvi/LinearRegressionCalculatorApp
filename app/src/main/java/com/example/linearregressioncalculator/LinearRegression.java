package com.example.linearregressioncalculator;

import java.util.ArrayList;

public class LinearRegression {
    private final double intercept, slope;
    public LinearRegression(ArrayList<Double> xal, ArrayList<Double> yal) {
        if (xal.size() != yal.size()) {
            throw new IllegalArgumentException("array lengths are not equal");
        }
        int n = xal.size();

        double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;
        for (int i = 0; i < n; i++) {
            sumx  += xal.get(i);
            sumx2 += xal.get(i)*xal.get(i);
            sumy  += yal.get(i);
        }
        double xbar = sumx / n;
        double ybar = sumy / n;

        double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
        for (int i = 0; i < n; i++) {
            xxbar += (xal.get(i) - xbar) * (xal.get(i) - xbar);
            yybar += (yal.get(i) - ybar) * (yal.get(i) - ybar);
            xybar += (xal.get(i) - xbar) * (yal.get(i) - ybar);
        }
        slope  = xybar / xxbar;
        intercept = ybar - slope * xbar;
    }
    public double intercept() {
        return intercept;
    }
    public double slope() {
        return slope;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("Y = %.4f X + %.4f", slope(), intercept()));
        return s.toString();
    }

}
