/** 
 * Returns current time. Absolute reference is not important as only time deltas are used. Extracting this to a separate method allows better testing.
 * @return current time in milliseconds
 */
protected long getCurrentTimeMs(){
  return SystemClock.uptimeMillis();
}
