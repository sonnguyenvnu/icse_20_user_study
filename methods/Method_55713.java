static Object stackWalkGetMethod(Class<?> after){
  StackTraceElement[] stackTrace=Thread.currentThread().getStackTrace();
  for (int i=3; i < stackTrace.length; i++) {
    if (!stackTrace[i].getClassName().startsWith(after.getName())) {
      return stackTrace[i];
    }
  }
  throw new IllegalStateException();
}
