package com.acme.edu;

import java.io.OutputStream;

public class Logger {


    public static void log(String message) {
        System.out.println("string: " + message);
    }

    public static void log(int message) {
        System.out.println("primitive: " + message);
    }

    public static void log(boolean b) {
        System.out.println("primitive: " + b);
    }

    public static void log(char c) {
        System.out.println("char: " + c);
    }

    public static void log(Object o) {
        System.out.println("reference: " + o);
    }


}
