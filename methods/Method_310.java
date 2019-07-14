private void moveToHead(Node<K,V> node){
  if (node.tail == null) {
    node.next.tail=null;
    tailer=node.next;
    nodeCount--;
  }
  if (node.next == null) {
    return;
  }
  if (node.tail != null && node.next != null) {
    node.tail.next=node.next;
    node.next.tail=node.tail;
    nodeCount--;
  }
  node=new Node<>(node.getKey(),node.getValue());
  addHead(node);
}
