/** 
 * ?????????
 * @param head
 */
public void reverseNode(Node head){
  if (head == null) {
    return;
  }
  Node node;
  Node pre=head;
  Node cur=head.next;
  Node next;
  while (cur != null) {
    next=cur.next;
    cur.next=pre;
    pre=cur;
    cur=next;
  }
  head.next=null;
  node=pre;
  while (node != null) {
    System.out.println(node.value);
    node=node.next;
  }
}
