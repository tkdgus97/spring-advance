package com.tkdgus.springadvance.v0;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalArgumentException("exception");
        }

        sleep(1000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
