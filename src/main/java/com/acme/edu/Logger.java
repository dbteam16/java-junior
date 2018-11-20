package com.acme.edu;

public class Logger {

    private static void print(String decoratedMessage) {System.out.println(decoratedMessage);}

    private static int accumulated = 0;
    private static String prevMethod = null;
    
    public static void log(String message) {
        String decoratedMessage = "" + message;

        flush();
        print(decoratedMessage);
        prevMethod = "logString";
    }

    public static void log(int message) {
        accumulated += message;
        prevMethod = "logInt";
    }

    public static void log(boolean b) {
        flush();
        print("" + b);
        prevMethod = "logBoolean";
    }

    public static void log(char c) {
        flush();
        print("char: " + c);
        prevMethod = "logChar";
    }

    public static void log(Object o) {
        flush();
        print("reference: " + o);
        prevMethod = "logObject";
    }

    public static void flush() {
        if ("logInt".equals(prevMethod)) {
            print("" + accumulated);
            accumulated = 0;
        }
        prevMethod = "flush";
    }
}
