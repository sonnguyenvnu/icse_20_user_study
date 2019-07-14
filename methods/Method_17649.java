private void onHit(Node node){
  if (node.type == QueueType.HOT) {
    node.moveToTail(headHot);
  }
 else   if (node.type == QueueType.WARM) {
    node.moveToTail(headWarm);
  }
 else   if (node.type == QueueType.COLD) {
    node.remove();
    sizeCold--;
    node.type=QueueType.WARM;
    node.appendToTail(headWarm);
    sizeWarm++;
    if (sizeWarm > maxWarm) {
      Node demoted=headWarm.next;
      demoted.remove();
      sizeWarm--;
      demoted.type=QueueType.COLD;
      demoted.appendToTail(headCold);
      sizeCold++;
    }
  }
 else {
    throw new IllegalStateException();
  }
}
