@Override public void finished(){
  for (int i=0; i < levels; i++) {
    int level=i;
    long count=data.values().stream().filter(node -> node.level == level).count();
    checkState(count == sizeQ[i]);
  }
}
