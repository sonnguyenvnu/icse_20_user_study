/** 
 * Log status.
 * @param text log information
 */
private static void log(String text){
  if (LOCAL_LOGV) {
    Timber.v(text);
  }
}
