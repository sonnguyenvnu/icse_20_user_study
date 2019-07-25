/** 
 * @return Date object that returns the current time in the default Timezone
 */
public static Date now(){
  if (!isInitialized()) {
    throw new IllegalStateException("You need to call init() on TrueTime at least once.");
  }
  long cachedSntpTime=_getCachedSntpTime();
  long cachedDeviceUptime=_getCachedDeviceUptime();
  long deviceUptime=SystemClock.elapsedRealtime();
  long now=cachedSntpTime + (deviceUptime - cachedDeviceUptime);
  return new Date(now);
}
