/** 
 * Prints full exception stack trace, from top to root cause, into a String.
 */
public static String exceptionChainToString(Throwable t){
  StringWriter sw=new StringWriter();
  PrintWriter pw=new PrintWriter(sw,true);
  while (t != null) {
    t.printStackTrace(pw);
    t=t.getCause();
  }
  StreamUtil.close(pw);
  StreamUtil.close(sw);
  return sw.toString();
}
