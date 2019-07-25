/** 
 * Inserts node into queue, initializing if necessary. See picture above.
 * @param node the node to insert
 * @return node's predecessor
 */
private Node enq(final Node node){
  for (; ; ) {
    Node t=tail;
    if (t == null) {
      if (compareAndSetHead(new Node()))       tail=head;
    }
 else {
      node.prev=t;
      if (compareAndSetTail(t,node)) {
        t.next=node;
        return t;
      }
    }
  }
}
