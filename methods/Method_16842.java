public ListNode addTwoNumbers(ListNode l1,ListNode l2){
  ListNode node=new ListNode(0);
  ListNode n1=l1, n2=l2, t=node;
  int sum=0;
  while (n1 != null || n2 != null) {
    sum/=10;
    if (n1 != null) {
      sum+=n1.val;
      n1=n1.next;
    }
    if (n2 != null) {
      sum+=n2.val;
      n2=n2.next;
    }
    t.next=new ListNode(sum % 10);
    t=t.next;
  }
  if (sum / 10 != 0)   t.next=new ListNode(1);
  return node.next;
}
