public ListNode deleteDuplicates(ListNode head){
  if (head == null || head.next == null)   return head;
  ListNode curr=head;
  while (curr.next != null) {
    if (curr.next.val == curr.val) {
      curr.next=curr.next.next;
    }
 else {
      curr=curr.next;
    }
  }
  return head;
}
