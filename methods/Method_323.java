/** 
 * ????????
 * @param node
 * @return
 */
public boolean isLoop(Node node){
  Node slow=node;
  Node fast=node.next;
  while (slow.next != null) {
    Object dataSlow=slow.data;
    Object dataFast=fast.data;
    if (dataFast == dataSlow) {
      return true;
    }
    if (fast.next == null) {
      return false;
    }
    slow=slow.next;
    fast=fast.next.next;
    if (fast == null) {
      return false;
    }
  }
  return false;
}
