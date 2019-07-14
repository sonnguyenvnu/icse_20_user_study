/** 
 * Prints stack trace into a String.
 */
public static String exceptionStackTraceToString(final Throwable t){
  StringWriter sw=new StringWriter();
  PrintWriter pw=new PrintWriter(sw,true);
  t.printStackTrace(pw);
  StreamUtil.close(pw);
  StreamUtil.close(sw);
  return sw.toString();
}
