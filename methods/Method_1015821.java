/** 
 * Constructs a pretty one line version of a  {@link Throwable}. Useful for debugging.
 * @param t the {@link Throwable} to format.
 * @return a string representing information about the {@link Throwable}
 */
public static String exception(Throwable t){
  StackTraceElement[] trace=t.getStackTrace();
  return t.getClass().getSimpleName() + " : " + t.getMessage() + ((trace.length > 0) ? " @ " + t.getStackTrace()[0].getClassName() + ":" + t.getStackTrace()[0].getLineNumber() : "");
}
