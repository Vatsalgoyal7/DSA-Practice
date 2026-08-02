class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Dummy node acts as a starting placeholder for the result list
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;

        // Loop as long as there are nodes to process or a leftover carry
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            // Calculate new carry and digit
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }

        // Return the actual head of the result list (skipping dummy)
        return dummy.next;
    }
}
