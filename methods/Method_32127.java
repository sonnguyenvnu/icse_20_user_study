/** 
 * Sets the current time to return the system time plus an offset. <p> This method changes the behaviour of  {@link #currentTimeMillis()}. Whenever the current time is queried,  {@link System#currentTimeMillis()} is usedand then offset by adding the millisecond value specified here.
 * @param offsetMillis  the fixed millisecond time to use
 * @throws SecurityException if the application does not have sufficient security rights
 */
public static final void setCurrentMillisOffset(long offsetMillis) throws SecurityException {
  checkPermission();
  if (offsetMillis == 0) {
    cMillisProvider=SYSTEM_MILLIS_PROVIDER;
  }
 else {
    cMillisProvider=new OffsetMillisProvider(offsetMillis);
  }
}
