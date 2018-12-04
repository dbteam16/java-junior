package com.acme.edu;

import java.util.Objects;

public class Logger {

    private static void print(String decoratedMessage) {System.out.println(decoratedMessage);}

    private static int accumulated = 0;
    private static String prevMethod = null;
    // support string Cache
    private static String strCache = null;
    private static int strCnt = 0;
    
    public static void log(String message) {
        if (!"logString".equals(prevMethod))
            flush();

        // previous wans't string - store message
        if (!"logString".equals(prevMethod)) {
            strCache = message;
            strCnt = 1;
        } else if (Objects.equals(strCache, message)) {
            // previous was string - now we have same
            ++strCnt;
        } else {
            // !Objects.equals(strCache, message)
            flush();
            strCache = message;
            strCnt=1;
        }

        prevMethod = "logString";
    }

    // test for overflow when log called for integer
    private static void increaseAccWithRangeCheck(int message, long maxValue) {
        long test = (long) accumulated + (long) message;
        if (test > maxValue) {
            flush();
            accumulated = message;
        } else {
            accumulated += message;
        }
    }

    public static void log(int message) {
        if (!"logInt".equals(prevMethod))
            flush();

        increaseAccWithRangeCheck(message, Integer.MAX_VALUE);
        prevMethod = "logInt";
    }

    public static void log(byte message) {
        if (!"logInt".equals(prevMethod))
            flush();

        increaseAccWithRangeCheck(message, Byte.MAX_VALUE);
        prevMethod = "logInt";
    }

    public static void log(boolean b) {
        flush();
        print("primitive: " + b);
        prevMethod = "logBoolean";
    }

    public static void log(char c) {
        flush();
        print("char: " + c);
        prevMethod = "logChar";
    }

    private static String convertIntArrayToString(int [] arr) {
        String res = "{";

        for(int i=0; i<arr.length; ++i) {
            if (i>0) {
                res += ", ";
            }
            res += arr[i];
        }

        res += "}";
        return res;
    }

    public static void log(int[] array) {
        flush();
        String res = "primitives array: ";
        res += convertIntArrayToString(array);
        print(res);
        prevMethod = "logIntegersArray";
    }

    public static void log(int[][] matrix) {
        flush();
        print("primitives matrix: {");
        for (int[] c:matrix) {
            print(convertIntArrayToString(c));
        }

        print("}");
        prevMethod = "logIntegersArray";
    }


    public static void log(Object o) {
        flush();
        print("reference: " + o);
        prevMethod = "logObject";
    }

    public static void flush() {
        flushInt();
        flushStr();
        prevMethod = "flush";
    }

    // expected to be called from flush() only
    private static void flushInt() {
        if ("logInt".equals(prevMethod)) {
            print("primitive: " + accumulated);
            accumulated = 0;
        }
    }

    // expected to be called from flush() only
    private static void flushStr() {
        if ("logString".equals(prevMethod)) {
            String s = strCache;
            if (strCnt > 1)
                s += " (x" + strCnt + ")";

            print("string: " + s);
            strCache = null;
            strCnt = 0;
        }
    }
}

