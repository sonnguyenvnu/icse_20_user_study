@Override public void startup() throws LifeCycleException {
  if (!isStarted) {
    isStarted=true;
    return;
  }
  throw new LifeCycleException("this component has started");
}
