/** 
 * Checks whether Apache Commons Logging is available.
 * @return {@code true} if it is, {@code false if it is not}
 */
public boolean isApacheCommonsLoggingAvailable(){
  if (apacheCommonsLoggingAvailable == null) {
    apacheCommonsLoggingAvailable=ClassUtils.isPresent("org.apache.commons.logging.Log",classLoader);
  }
  return apacheCommonsLoggingAvailable;
}
