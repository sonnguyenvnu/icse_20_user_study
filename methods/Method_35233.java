@Override @NonNull @CheckResult public final <T>LifecycleTransformer<T> bindToLifecycle(){
  return RxControllerLifecycle.bindController(lifecycleSubject);
}
