@Override public <T>LifecycleTransformer<T> bindToLifecycle(){
  return LifeCycleHelper.bindToLifeCycle(lifecycleSubject,correspondingEvents);
}
