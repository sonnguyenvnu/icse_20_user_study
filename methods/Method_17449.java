/** 
 * Add meta data after hand hot, evict data if required, and update hands accordingly. 
 */
private void add(Node node){
  evict();
  if (handHot == null) {
    handHot=handCold=handTest=node;
    node.next=node.prev=node;
  }
 else {
    node.prev=handHot;
    node.next=handHot.next;
    handHot.next.prev=node;
    handHot.next=node;
  }
  if (handCold == handHot) {
    handCold=node.next;
  }
  if (handTest == handHot) {
    handTest=node.next;
  }
  handHot=node.next;
}
