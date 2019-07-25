public static SafeLifecycleListener wrap(LifecycleListener listener){
  Preconditions.checkNotNull(listener,"listener argument must be non-null");
  return new SafeLifecycleListener(listener,null);
}
