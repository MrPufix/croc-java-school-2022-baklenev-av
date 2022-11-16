package ru.croc.task10;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Solution {
    private static final int PASSWORD_LENGTH = 7;

    private static long maxNumber(int length) {
        long max = 0;
        long radixPower = 1;
        int radix = 26;
        for (int i = 0; i < length; i++) {
            radixPower = i == 0 ? 1 : radixPower * radix;
            max = max + radixPower;
        }
        return max * (radix - 1);
    }

    private static Future<String>[] startBruteForceThreads(int threadsNumber, String passwordHash,
                                                           int passwordLength, ExecutorService pool) {
        Future<String>[] threads = new Future[threadsNumber];

        long max = maxNumber(passwordLength);
        long lowerBound = 0;
        long step = max / threadsNumber;
        for (int i = 0; i < threadsNumber; i++) {
            long upperBound = i != threadsNumber - 1 ? lowerBound + step : max;
            threads[i] = pool.submit(new BruteForcePassword(lowerBound, upperBound, passwordLength, passwordHash));
            lowerBound += step + 1;
        }

        return threads;
    }

    /**
     * Find password matching given hash, using multithreading.
     * Password contains only lowercase latin letters.
     * @param threadsNumber number of threads to use
     * @param passwordHash hash of password to find
     * @param passwordLength length of password to find
     * @return password
     * @throws ExecutionException if computation threw an exception
     * @throws InterruptedException if thread was interrupted
     */
    public static String calculatePassword(int threadsNumber, String passwordHash, int passwordLength)
            throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(threadsNumber);

        Future<String>[] results = startBruteForceThreads(threadsNumber, passwordHash, passwordLength, pool);

        StringBuilder password = new StringBuilder();

        for (Future<String> result : results) {
            password.append(result.get());
        }

        pool.shutdown();
        BruteForcePassword.found = false;

        return password.toString();
    }

    /**
     * Find password matching given hash, using multithreading.
     * Password contains only lowercase latin letters.
     * Password length is seven characters.
     * @param threadsNumber number of threads to use
     * @param passwordHash hash of password to find
     * @return password
     */
    public static String calculatePassword(int threadsNumber, String passwordHash) {
        try {
            return calculatePassword(threadsNumber, passwordHash, PASSWORD_LENGTH);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
