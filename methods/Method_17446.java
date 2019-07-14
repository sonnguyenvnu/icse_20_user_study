@Override public void record(long key){
  Node node=data.get(key);
  if (node == null) {
    onMiss(key);
  }
 else   if (node.status == Status.TEST) {
    onNonResidentHit(node);
  }
 else {
    onHit(node);
  }
}
