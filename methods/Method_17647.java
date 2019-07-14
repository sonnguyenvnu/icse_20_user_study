@Override public void finished(){
  long windowSize=data.values().stream().filter(n -> n.status == Status.WINDOW).count();
  long probationSize=data.values().stream().filter(n -> n.status == Status.PROBATION).count();
  long protectedSize=data.values().stream().filter(n -> n.status == Status.PROTECTED).count();
  checkState(windowSize == sizeWindow);
  checkState(protectedSize == sizeProtected);
  checkState(probationSize == data.size() - windowSize - protectedSize);
  checkState(data.size() <= maximumSize);
}
