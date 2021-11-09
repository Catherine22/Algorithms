package com.catherine.trees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author : Catherine
 */
public class LLBTTreeTest {
    private LLRBTree<String, String> tree;

    @BeforeEach
    void setUp() {
        tree = new LLRBTree<>();
    }

    @Test
    void insert() {
        tree.insert("S", "S");
        Assertions.assertEquals(tree.get("S"), "S");
    }
}
