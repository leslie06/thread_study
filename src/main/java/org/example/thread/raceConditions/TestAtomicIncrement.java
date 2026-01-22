package org.example.thread.raceConditions;

import java.util.concurrent.atomic.AtomicLong;

public class TestAtomicIncrement {
    private AtomicLong count = new AtomicLong(0);

    public void increment() {
        count.incrementAndGet();
    }

    public long getCount() {
        return count.get();
    }
}
