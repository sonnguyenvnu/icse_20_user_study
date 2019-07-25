private boolean logpass(Throwable e){
  return e instanceof LifecycleLogpassException || e instanceof LifecycleLogPassRuntimeException;
}
