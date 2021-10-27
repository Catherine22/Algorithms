package com.catherine.trees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author : Catherine
 */
public class LLBTTreeTest {
    LLRBTree<String, String> tree = new LLRBTree<>();

    @Test
    void insert() {
        tree.insert("S", "S");
        Assertions.assertEquals(tree.get("S"), "S");
    }
}
