@Override public LifecycleInjector build(){
  try {
    return new LifecycleInjector(this);
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
}
