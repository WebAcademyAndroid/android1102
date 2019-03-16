package com.example.android5;

public class Student {
    public String firstName;
    public String lastName;
    public int age;

    public Student() {
    }

    public Student(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s, age: %d", firstName, lastName, age);
    }
}
