public void register(SNode node,Runnable closure){
  MapSequence.fromMap(allActions).put(node,closure);
}
