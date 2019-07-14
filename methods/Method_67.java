private int plusOneRecursive(ListNode head){
  if (head == null) {
    return 1;
  }
  int carry=plusOneRecursive(head.next);
  if (carry == 0) {
    return 0;
  }
  int value=head.val + 1;
  head.val=value % 10;
  return value / 10;
}
