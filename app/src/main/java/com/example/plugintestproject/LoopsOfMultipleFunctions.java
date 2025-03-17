package com.example.plugintestproject;

public class LoopsOfMultipleFunctions {

    public void functionOne(int count) {
        System.out.println("functionOne is running... count = " + count);
        if (count < 3) {
            functionTwo(count + 1);
        }
    }

    public void functionTwo(int value) {
        System.out.println("functionTwo is running... value = " + value);
        if (value < 3) {
            functionThree(value + 1);
        }
    }

    public void functionThree(int number) {
        System.out.println("functionThree is running... number = " + number);
        // If this condition holds, we loop back to functionOne, forming a cycle.
        if (number < 3) {
            functionOne(number + 1);
        }
    }
}