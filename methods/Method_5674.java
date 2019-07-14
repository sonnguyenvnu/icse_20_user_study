/** 
 * Returns whether the track at the specified index in the selection is blacklisted.
 * @param index The index of the track in the selection.
 * @param nowMs The current time in the timebase of {@link SystemClock#elapsedRealtime()}.
 */
protected final boolean isBlacklisted(int index,long nowMs){
  return blacklistUntilTimes[index] > nowMs;
}
