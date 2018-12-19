package com.example.piotrjanus.lab8zpo;

public enum Gender {
    MALE,
    FEMALE;

    public static Gender of(String gender){
        Gender g;
        if (gender.equals("Male"))
            g = Gender.MALE;
        else
            g = Gender.FEMALE;

        return g;
    }
}
