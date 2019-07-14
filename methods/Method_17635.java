@Override public void finished(){
  for (int i=0; i < levels; i++) {
    int level=i;
    int count=(int)data.values().stream().filter(node -> node.status == Status.MAIN).filter(node -> node.level == level).count();
    checkState(count == sizeMainQ[i]);
  }
  checkState(data.values().stream().filter(n -> n.status == Status.WINDOW).count() == sizeWindow);
  checkState(data.size() <= maxWindow + maxMain);
}
