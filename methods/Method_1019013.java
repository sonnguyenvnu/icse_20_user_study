/** 
 * Print an exception.
 * @param exception Exception to print.
 * @param display Show error in UI.
 */
public static void error(Throwable exception,boolean display){
  Logging.error(exception,display,false);
}
