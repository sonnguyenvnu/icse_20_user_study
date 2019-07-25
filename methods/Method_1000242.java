/** 
 * Push to tail
 * @param node
 */
private void offer(DLinkList node){
  tail.left.right=node;
  node.left=tail.left;
  node.right=tail;
  tail.left=node;
}
