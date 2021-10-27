package com.catherine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * List - ArrayList
 *      - LinkedList
 *
 * Set  - HashSet
 *      - TreeSet
 *      - LinkedHashSet
 *
 * Map  - HashMap
 *      - TreeMap
 *      - LinkedHashMap
 *
 * @author : Catherine
 */
public class Collections {

    @Test
    void testLinkedList() {
        List<String> list = new LinkedList<>();
        list.add("A");
        Assertions.assertTrue(list.contains("A"));

        list.set(list.indexOf("A"), "B");
        Assertions.assertTrue(list.contains("B"));

        list.remove("B");
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void testArrayList() {
        List<String> list = new ArrayList<>();
        list.add("A");
        Assertions.assertTrue(list.contains("A"));

        list.set(list.indexOf("A"), "B");
        Assertions.assertTrue(list.contains("B"));

        list.remove("B");
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void testHashSet() {
        Set<String> set = new HashSet<>();
        set.add("A");
        set.add("A");
        Assertions.assertTrue(set.contains("A"));
        Assertions.assertEquals(1, set.size());

        // Two ways to loop a set
        for (String s : set) {
            Assertions.assertEquals("A", s);
        }
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            Assertions.assertEquals("A", it.next());
        }

        set.remove("A");
        Assertions.assertTrue(set.isEmpty());
    }

    /**
     * A tree set is an ordered set
     */
    @Test
    void testTreeSet() {
        Set<String> set = new TreeSet<>();
        set.add("C");
        set.add("B");
        set.add("A");
        set.add("A");
        Assertions.assertEquals(3, set.size());

        // Two ways to loop a set
        for (String s : set) {
            Assertions.assertTrue(set.contains(s));
        }
        Iterator<String> it = set.iterator();
        String s;
        while (it.hasNext()) {
            s = it.next();
            if (!s.equals("A")) {
                Assertions.assertNotEquals("A", s);
                break;
            }
        }

        String[] s1 = new String[3];
        set.toArray(s1);
        String[] s2 = {"A", "B", "C"};
        Assertions.assertArrayEquals(s2, s1);
    }

    /**
     * A linkedHashSet is a set that ordered items by insertion order
     */
    @Test
    void testLinkedHashSet() {
        Set<String> set = new LinkedHashSet<>();
        set.add("C");
        set.add("B");
        set.add("A");
        set.add("A");
        Assertions.assertEquals(3, set.size());

        // Two ways to loop a set
        for (String s : set) {
            Assertions.assertTrue(set.contains(s));
        }
        Iterator<String> it = set.iterator();
        String s;
        while (it.hasNext()) {
            s = it.next();
            if (!s.equals("A")) {
                Assertions.assertNotEquals("A", s);
                break;
            }
        }

        String[] s1 = new String[3];
        set.toArray(s1);
        String[] s2 = {"C", "B", "A"};
        Assertions.assertArrayEquals(s2, s1);
    }

    @Test
    void testHashMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(66, "B");
        map.put(65, "A");
        Assertions.assertTrue(map.containsKey(65));
        Assertions.assertTrue(map.containsValue("B"));
        Assertions.assertEquals(2, map.size());

        // Two ways to loop a map
        map.forEach((k, v) -> Assertions.assertEquals(v, map.get(k)));
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        Map.Entry<Integer, String> entry;
        while (it.hasNext()) {
            entry = it.next();
            Assertions.assertEquals(entry.getValue(), map.get(entry.getKey()));
        }
    }

    @Test
    void testTreeMap() {
        Map<Integer, String> map = new TreeMap<>();
        map.put(67, "C");
        map.put(66, "B");
        map.put(65, "A");
        Assertions.assertTrue(map.containsKey(65));
        Assertions.assertTrue(map.containsValue("B"));
        Assertions.assertEquals(3, map.size());

        int count = 0;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (count == 0) {
                Assertions.assertEquals(65, entry.getKey());
            } else if (count == 1) {
                Assertions.assertEquals("B", entry.getValue());
            }
            count++;
        }
    }

    @Test
    void testLinkedHashMap() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(67, "C");
        map.put(66, "B");
        map.put(65, "A");
        Assertions.assertTrue(map.containsKey(65));
        Assertions.assertTrue(map.containsValue("B"));
        Assertions.assertEquals(3, map.size());

        int count = 0;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (count == 0) {
                Assertions.assertEquals(67, entry.getKey());
            } else if (count == 1) {
                Assertions.assertEquals("B", entry.getValue());
            }
            count++;
        }
    }

}
