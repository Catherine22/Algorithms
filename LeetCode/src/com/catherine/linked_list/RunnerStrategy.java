package com.catherine.linked_list;

/**
 * @author : Catherine
 * @created : 17/11/2020
 * <p>
 * Linked List Cycle
 * <p>
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 * <p>
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
 * Example 3:
 * <p>
 * <p>
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of the nodes in the list is in the range [0, 104].
 * -105 <= Node.val <= 105
 * pos is -1 or a valid index in the linked-list.
 * <p>
 * <p>
 * Follow up: Can you solve it using O(1) (i.e. constant) memory?
 * <p>
 * https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1212/
 */

public class RunnerStrategy {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode walker = head;
        ListNode runner = head;
        while (runner != null && runner.next != null) {
            runner = runner.next.next;
            walker = walker.next;

            // there exists a loop
            if (runner == walker) {
                return true;
            }
        }

        return false;
    }
}
