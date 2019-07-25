/** 
 * Make sure that recorded value is within a supported range.
 */
private long narrow(long latencyNano){
  if (latencyNano < LOWEST_DISCERNIBLE_VALUE) {
    return LOWEST_DISCERNIBLE_VALUE;
  }
  if (latencyNano > HIGHEST_TRACKABLE_VALUE) {
    return HIGHEST_TRACKABLE_VALUE;
  }
  return latencyNano;
}
