private void adjust(){
  currentTime++;
  for (int i=1; i < headQ.length; i++) {
    Node node=headQ[i].next;
    if (node.next.expireTime < currentTime) {
      node.remove();
      node.queueIndex=(i - 1);
      node.appendToTail(headQ[node.queueIndex]);
      node.expireTime=currentTime + lifetime;
    }
  }
}
