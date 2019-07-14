/** 
 * Delete meta data data, update hands accordingly. 
 */
private void delete(Node node){
  if (handHot == node) {
    handHot=node.next;
  }
  if (handCold == node) {
    handCold=node.next;
  }
  if (handTest == node) {
    handTest=node.next;
  }
  node.remove();
}
