/** 
 * ?????
 * @param node
 */
private void addHead(Node<K,V> node){
  header.next=node;
  node.tail=header;
  header=node;
  nodeCount++;
  if (nodeCount == 2) {
    tailer.next.next.tail=null;
    tailer=tailer.next.next;
  }
}
