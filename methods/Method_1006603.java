@Override public void shutdown() throws LifeCycleException {
  if (isStarted) {
    isStarted=false;
    return;
  }
  throw new LifeCycleException("this component has closed");
}
