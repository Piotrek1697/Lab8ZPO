package com.example.piotrjanus.lab8zpo;

public class PPM {

    public static double getPPM(double height, double weight, long age, String method, Gender gender) {

        double PPM = 0;

        if (gender == Gender.FEMALE)
            PPM = 655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * (double) age);
        else if (gender == Gender.MALE)
            PPM = 66.5 + (13.75 * weight) + (5.003 * height) - (6.775 * (double) age);

        return PPM;

    }
}
