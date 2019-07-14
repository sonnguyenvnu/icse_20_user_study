/** 
 * Returns the stacktrace of the given Throwable as a String.
 * @since 4.13
 */
public static String getStacktrace(Throwable exception){
  StringWriter stringWriter=new StringWriter();
  PrintWriter writer=new PrintWriter(stringWriter);
  exception.printStackTrace(writer);
  return stringWriter.toString();
}
