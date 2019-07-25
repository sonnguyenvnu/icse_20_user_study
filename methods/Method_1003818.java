@Override public Throwable apply(Throwable throwable,Object o){
  Throwable t;
  if (throwable instanceof UnsupportedOperationException) {
    t=throwable.getCause();
  }
 else   if (throwable instanceof UndeclaredThrowableException) {
    t=((UndeclaredThrowableException)throwable).getUndeclaredThrowable();
  }
 else {
    t=throwable;
  }
  if (Execution.isActive()) {
    Promise.error(t).then(Action.noop());
  }
  return t;
}
