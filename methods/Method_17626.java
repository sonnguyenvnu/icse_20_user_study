@Override public void finished(){
  checkState(data.values().stream().filter(n -> n.status == Status.WINDOW).count() == sizeWindow);
  checkState(sizeWindow + sizeMain == data.size());
  checkState(data.size() <= maxWindow + maxMain);
}
