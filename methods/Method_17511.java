private void onMiss(long key){
  Node node=new Node(key);
  data.put(key,node);
  node.appendToTail(headQ[0]);
  sizeQ[0]++;
  adjust();
  evict(node);
}
