private ListNode reverse(ListNode pre,ListNode next){
  ListNode head=pre.next;
  ListNode move=head.next;
  while (move != next) {
    head.next=move.next;
    move.next=pre.next;
    pre.next=move;
    move=head.next;
  }
  return head;
}
