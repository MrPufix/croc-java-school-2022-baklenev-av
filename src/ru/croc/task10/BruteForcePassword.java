package ru.croc.task10;

import java.util.concurrent.Callable;

public class BruteForcePassword implements Callable<String> {
    public static class FoundSync {public volatile boolean found = false;}

    private final FoundSync foundSyncObject;
    private final long lowerBound;
    private final long upperBound;
    private final int passwordLength;
    private final String passwordHash;

    public BruteForcePassword(long lowerBound, long upperBound, int passwordLength, String passwordHash, FoundSync foundSyncObject) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.passwordLength = passwordLength;
        this.passwordHash = passwordHash;
        this.foundSyncObject = foundSyncObject;
    }

    private static String numberToPassword(long number, int passwordLength) {
        String s = Long.toString(number, 26);
        StringBuilder res = new StringBuilder();

        char[] chars = s.toCharArray();

        res.append("a".repeat(Math.max(0, passwordLength - chars.length)));

        for(char c : chars) {
            if(Character.isDigit(c))
                res.append((char)(c + 49));
            else
                res.append((char)(c + 10));
        }

        return res.toString();
    }

    private static String getNextPassword(String password) {
        boolean add = true;

        char[] chars = password.toCharArray();

        for (int i = chars.length-1; add && i >= 0; i--) {
            chars[i] += 1;
            add = false;
            if(chars[i] > 122) {
                add = true;
                chars[i] = 'a';
            }
        }

        return String.valueOf(chars);
    }


    @Override
    public String call() {
        String password = numberToPassword(lowerBound, passwordLength);
        for(long number = lowerBound; number <= upperBound; number++) {
            if(HashGenerator.hashPassword(password).equals(passwordHash)) {
                foundSyncObject.found = true;
                return password;
            }
            if(foundSyncObject.found) {
                return "";
            }

            password = getNextPassword(password);
        }

        return "";
    }
}
