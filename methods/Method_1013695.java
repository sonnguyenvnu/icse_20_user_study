default void destroy(T t) throws Exception {
  LifecycleHelper.stopIfPossible(t);
}
