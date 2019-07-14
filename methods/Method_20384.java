@Override public String toString(){
  String string="Epoxy Processor Exception: " + getMessage();
  Throwable cause=getCause();
  if (cause != null) {
    string+=" Caused by " + cause.getClass().getSimpleName() + ": " + cause.getMessage();
    StackTraceElement[] stackTrace=getStackTrace();
    boolean firstTraceElement=true;
    if (stackTrace.length > 0) {
      for (      StackTraceElement stackTraceElement : stackTrace) {
        if (stackTraceElement.getClassName().contains("epoxy") && !stackTraceElement.getClassName().contains(ErrorLogger.class.getSimpleName()) && !stackTraceElement.getClassName().contains("buildEpoxyException")) {
          if (firstTraceElement) {
            string+="       Stacktrace:      ";
            firstTraceElement=false;
          }
 else {
            string+="       ";
          }
          string+=stackTraceElement;
        }
      }
    }
  }
  return string;
}
