/** 
 * Converts granule value to time.
 * @param granule The granule value.
 * @return Time in milliseconds.
 */
protected long convertGranuleToTime(long granule){
  return (granule * C.MICROS_PER_SECOND) / sampleRate;
}
