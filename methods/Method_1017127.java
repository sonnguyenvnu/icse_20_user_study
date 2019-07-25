@Override public LifeCycle build(final String id,final LifeCycles instance){
  return new ManagedLifeCycle(id,instance);
}
