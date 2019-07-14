private void onHit(Node node){
  node.remove();
  sizeQ[node.level]--;
  if (node.level < (levels - 1)) {
    node.level++;
  }
  Node head=headQ[node.level];
  node.appendToTail(head);
  sizeQ[node.level]++;
  adjust();
}
