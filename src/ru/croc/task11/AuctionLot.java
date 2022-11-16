package ru.croc.task11;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class AuctionLot {
    private static final ReentrantLock lock = new ReentrantLock();
    private volatile int currentPrice;
    private volatile String bettorName;
    private final LocalDateTime endTime;

    public AuctionLot(int currentPrice, String bettorName, LocalDateTime endTime) {
        this.currentPrice = currentPrice;
        this.bettorName = bettorName;
        this.endTime = endTime;
    }

    public void bet(int newPrice, String bettor) {
        lock.lock();
        try {
            if(lotClosed()) return;
            if(newPrice > currentPrice) {
                currentPrice = newPrice;
                bettorName = bettor;
            }
        } finally {
            lock.unlock();
        }
    }

    public String getWinnerName() {
        return bettorName;
    }

    private boolean lotClosed() {
        return (int)Duration.between(endTime, LocalDateTime.now()).toMinutes() >= 0;
    }
}
