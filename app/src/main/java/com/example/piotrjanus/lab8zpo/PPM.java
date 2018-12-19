package com.example.piotrjanus.lab8zpo;

import android.content.Context;

public class PPM {

    private Context context;

    public PPM(Context context) {
        this.context = context;
    }

    public double getPPM(double height, double weight, long age, String method, Gender gender) {

        double PPM = 0;

        String[] methods = context.getResources().getStringArray(R.array.ppm_method);

        if (method.equals(methods[0])) {
            if (gender == Gender.FEMALE)
                PPM = 655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * (double) age);
            else if (gender == Gender.MALE)
                PPM = 66.5 + (13.75 * weight) + (5.003 * height) - (6.775 * (double) age);
        }else if (method.equals(methods[1])){
            if (gender == Gender.FEMALE)
                PPM = 10 * weight + 6.25 * height - 5 * age - 161;
            else if (gender == Gender.MALE)
                PPM = 10 * weight + 6.25 * height - 5 * age + 5;
        }

        return PPM;

    }
}
