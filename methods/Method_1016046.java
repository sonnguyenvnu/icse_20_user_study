@Override public Void call() throws Exception {
  Object target=(targetReference == null) ? this.target : targetReference.get();
  if (target != null) {
    for (    LifecycleAction m : actions) {
      m.call(target);
    }
  }
  return null;
}
