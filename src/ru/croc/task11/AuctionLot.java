package ru.croc.task11;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class AuctionLot {
    private static final ReentrantLock lock = new ReentrantLock();
    private volatile int currentPrice;
    private volatile String bettorName;
    private final LocalDateTime endTime;

    public AuctionLot(int startPrice, LocalDateTime endTime) {
        currentPrice = startPrice;
        this.endTime = endTime;
        bettorName = "";
    }

    public void bet(int newPrice, String bettor) {
        if(lotClosed() || newPrice <= currentPrice) return;
        lock.lock();
        try {
            if(newPrice > currentPrice) {
                currentPrice = newPrice;
                bettorName = bettor;
            }
        } finally {
            lock.unlock();
        }
    }

    public String getWinnerName() {
        if(!lotClosed())
            return "";
        return bettorName;
    }

    private boolean lotClosed() {
        return (int)Duration.between(endTime, LocalDateTime.now()).toMinutes() >= 0;
    }
}
