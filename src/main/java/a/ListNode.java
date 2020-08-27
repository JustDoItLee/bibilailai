package a;

/* 基本的单链表节点 */
public class ListNode {
    Integer val;
    ListNode next;

    void traverse(ListNode head) {
        for (ListNode p = head; p != null; p = p.next) {
            //p.val
        }
    }

    void traverse2(ListNode head) {
        traverse2(head);
    }
}
