package com.yourcompany.mongodb_objectid_generator.objectid;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class ObjectId {
    private final byte[] randomBytes;
    private static final AtomicInteger counter = new AtomicInteger(new SecureRandom().nextInt());

    public ObjectId() {
        this.randomBytes = new byte[5];
        new SecureRandom().nextBytes(this.randomBytes);
    }

    public byte[] generateObjectId() {
        byte[] objectId = new byte[12];
        int timestamp = (int) Instant.now().getEpochSecond();
        objectId[0] = (byte) (timestamp >> 24);
        objectId[1] = (byte) (timestamp >> 16);
        objectId[2] = (byte) (timestamp >> 8);
        objectId[3] = (byte) timestamp;

        System.arraycopy(randomBytes, 0, objectId, 4, 5);

        int count = counter.getAndIncrement();
        objectId[9] = (byte) (count >> 16);
        objectId[10] = (byte) (count >> 8);
        objectId[11] = (byte) count;

        return objectId;
    }

    @Override
    public String toString() {
        return bytesToHex(generateObjectId());
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }
}
