@Override public void finished(){
  long windowProbationSize=data.values().stream().filter(n -> n.status == Status.WINDOW_PROBATION).count();
  long windowProtectedSize=data.values().stream().filter(n -> n.status == Status.WINDOW_PROTECTED).count();
  long mainProtectedSize=data.values().stream().filter(n -> n.status == Status.MAIN_PROTECTED).count();
  checkState(sizeWindow <= maxWindow);
  checkState(windowProtectedSize == sizeWindowProtected);
  checkState(sizeWindow == windowProbationSize + sizeWindowProtected);
  checkState(mainProtectedSize == sizeMainProtected);
  checkState(data.size() <= maximumSize);
}
