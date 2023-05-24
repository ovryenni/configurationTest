package org.example;

public class Simple {
    public static void fibonaci(int input) {
        int v1 = 0, v2 = 0, v3 = 0;
        for (int i = 0; i < input; i++) {
            if (i == 0) {
                System.out.print("0,");
                continue;
            } else if (i == 1) {
                System.out.print("1,");
                v2 = 1;
                continue;
            }
            v3 = v1 + v2;
            System.out.print(v3 + ",");
            v1 = v2;
            v2 = v3;
        }
    }

    public static String reverse(String string) {
        System.out.println("");
        String res = "";
        for (int i = string.length() - 1; i >= 0; i--) {
            res += string.charAt(i);
        }
        return res;
    }

    public static void palindrom(String string) {
        String reverse = reverse(string);
        if (reverse.equalsIgnoreCase(string)) {
            System.out.println(string + " is palindrome");
        } else
            System.out.println(string + " is not palindrome");
    }

    public static void main(String[] args) {
        int input = 10;
        fibonaci(input);
        String string = "ovry";
        String string2 = "malam";
        System.out.print(reverse(string));
        palindrom(string);
        palindrom(string2);
    }
}

