package com.yourcompany.mongodb_objectid_generator.objectid;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObjectIdTest {

    @Test
    void testObjectIdGeneration() {
        ObjectId objectId = new ObjectId();
        String generatedId = objectId.toString();
        assertEquals(24, generatedId.length());
        assertTrue(generatedId.matches("[0-9A-F]+"));
    }
}
