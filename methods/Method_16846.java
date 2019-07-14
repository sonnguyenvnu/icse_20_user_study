public ListNode removeNthFromEnd(ListNode head,int n){
  ListNode pre=head;
  ListNode afterPreN=head;
  while (n-- != 0) {
    afterPreN=afterPreN.next;
  }
  if (afterPreN != null) {
    while (afterPreN.next != null) {
      pre=pre.next;
      afterPreN=afterPreN.next;
    }
    pre.next=pre.next.next;
  }
 else {
    head=head.next;
  }
  return head;
}
