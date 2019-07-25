/** 
 * This is an O(n) operation as we run through all the nodes and count them.<br> The accuracy of the value returned by this method is subject to races with producer/consumer threads. In particular when racing with the consumer thread this method may under estimate the size.<br> Note that passing nodes between queues, or concurrent requeuing of nodes can cause this method to return strange values.
 */
public int size(){
  final Node stub=this.stub;
  Node chaserNode=lvConsumerNode();
  if (chaserNode == stub) {
    chaserNode=chaserNode.getNext();
  }
  final Node producerNode=lvProducerNode();
  int size=0;
  while (chaserNode != null && chaserNode != stub && size < Integer.MAX_VALUE) {
    if (chaserNode == producerNode) {
      return size + 1;
    }
    chaserNode=chaserNode.getNext();
    size++;
  }
  return size;
}
