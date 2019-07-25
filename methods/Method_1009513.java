public static Node insert(Node head,int data){
  Node p=new Node(data);
  if (head == null)   head=p;
 else   if (head.next == null)   head.next=p;
 else {
    Node start=head;
    while (start.next != null)     start=start.next;
    start.next=p;
  }
  return head;
}
