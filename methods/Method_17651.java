private void reclaimfor(Node node){
  if ((sizeMain + sizeIn) < maximumSize) {
    data.put(node.key,node);
  }
 else   if (sizeIn > maxIn) {
    Node n=headIn.next;
    n.remove();
    sizeIn--;
    n.appendToTail(headOut);
    n.type=QueueType.OUT;
    sizeOut++;
    if (sizeOut > maxOut) {
      policyStats.recordEviction();
      Node victim=headOut.next;
      data.remove(victim.key);
      victim.remove();
      sizeOut--;
    }
    data.put(node.key,node);
  }
 else {
    policyStats.recordEviction();
    Node victim=headMain.next;
    data.remove(victim.key);
    victim.remove();
    sizeMain--;
    data.put(node.key,node);
  }
}
