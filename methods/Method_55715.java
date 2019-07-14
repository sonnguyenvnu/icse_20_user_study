@Nullable static Object stackWalkCheckPop(Class<?> after,Object pushedObj){
  StackTraceElement[] stackTrace=Thread.currentThread().getStackTrace();
  for (int i=3; i < stackTrace.length; i++) {
    StackTraceElement element=stackTrace[i];
    if (element.getClassName().startsWith(after.getName())) {
      continue;
    }
    StackTraceElement pushed=(StackTraceElement)pushedObj;
    if (isSameMethod(element,pushed)) {
      return null;
    }
    if (isAutoCloseable(element,pushed) && i + 1 < stackTrace.length) {
      element=stackTrace[i + 1];
      if (isSameMethod(pushed,stackTrace[i + 1])) {
        return null;
      }
    }
    return element;
  }
  throw new IllegalStateException();
}
