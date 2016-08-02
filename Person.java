package com.hathy.listsandcards;

import android.graphics.Bitmap;

class Person {
    String name;
    String age;
    Bitmap bitmap;

    Person(String name, String age, Bitmap bitmap) {
        this.name = name;
        this.age = age;
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }
}