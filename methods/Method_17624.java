@Override public void record(long key){
  policyStats.recordOperation();
  Node node=data.get(key);
  admittor.record(key);
  if (node == null) {
    node=new Node(key,Status.WINDOW);
    node.appendToTail(headWindow);
    data.put(key,node);
    sizeWindow++;
    evict();
    policyStats.recordMiss();
  }
 else   if (node.status == Status.WINDOW) {
    node.moveToTail(headWindow);
    policyStats.recordHit();
  }
 else   if (node.status == Status.MAIN) {
    node.moveToTail(headMain);
    policyStats.recordHit();
  }
 else {
    throw new IllegalStateException();
  }
}
