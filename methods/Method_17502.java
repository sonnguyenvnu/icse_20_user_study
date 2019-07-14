@Override public void record(long key){
  Node old=data.get(key);
  admittor.record(key);
  if (old == null) {
    Node node=new Node(key,sentinel);
    policyStats.recordMiss();
    data.put(key,node);
    node.appendToTail();
    evict(node);
  }
 else {
    policyStats.recordHit();
    policy.onAccess(old,policyStats);
  }
}
