@SuppressWarnings("deprecation") private List<Throwable> getCauses(Throwable cause){
  if (cause instanceof InvocationTargetException) {
    return getCauses(cause.getCause());
  }
  if (cause instanceof InvalidTestClassError) {
    return singletonList(cause);
  }
  if (cause instanceof InitializationError) {
    return ((InitializationError)cause).getCauses();
  }
  if (cause instanceof org.junit.internal.runners.InitializationError) {
    return ((org.junit.internal.runners.InitializationError)cause).getCauses();
  }
  return singletonList(cause);
}
