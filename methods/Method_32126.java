/** 
 * Resets the current time to return the system time. <p> This method changes the behaviour of  {@link #currentTimeMillis()}. Whenever the current time is queried,  {@link System#currentTimeMillis()} is used.
 * @throws SecurityException if the application does not have sufficient security rights
 */
public static final void setCurrentMillisSystem() throws SecurityException {
  checkPermission();
  cMillisProvider=SYSTEM_MILLIS_PROVIDER;
}
