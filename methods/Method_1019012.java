/** 
 * Print an exception as a warning.
 * @param exception Exception to print.
 */
public static void warn(Exception exception){
  String message=getErrorMessage(exception);
  Logging.warn(message);
}
