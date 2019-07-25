@Override public E dequeue(){
  if (isEmpty())   throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
  Node retNode=head;
  head=head.next;
  retNode.next=null;
  if (head == null)   tail=null;
  size--;
  return retNode.e;
}
