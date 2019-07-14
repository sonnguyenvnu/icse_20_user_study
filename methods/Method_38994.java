/** 
 * Returns called class.
 */
protected String getCallerClass(){
  Exception exception=new Exception();
  StackTraceElement[] stackTrace=exception.getStackTrace();
  for (  StackTraceElement stackTraceElement : stackTrace) {
    String className=stackTraceElement.getClassName();
    if (className.equals(SimpleLoggerProvider.class.getName())) {
      continue;
    }
    if (className.equals(SimpleLogger.class.getName())) {
      continue;
    }
    if (className.equals(Logger.class.getName())) {
      continue;
    }
    return shortenClassName(className) + '.' + stackTraceElement.getMethodName() + ':' + stackTraceElement.getLineNumber();
  }
  return "N/A";
}
