/** 
 * Retrives the exact location where this exception was thrown.
 * @param e The exception.
 * @return The location, suitable for a debug message.
 */
public static String getThrowLocation(Throwable e){
  StackTraceElement element=e.getStackTrace()[0];
  int lineNumber=element.getLineNumber();
  return element.getClassName() + "." + element.getMethodName() + (lineNumber < 0 ? "" : ":" + lineNumber) + (element.isNativeMethod() ? " [native]" : "");
}
