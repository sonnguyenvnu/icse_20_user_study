@Override public LifeCycle build(final LifeCycles instance){
  return build(instance.getClass().getCanonicalName(),instance);
}
