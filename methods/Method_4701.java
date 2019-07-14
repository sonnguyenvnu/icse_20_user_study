/** 
 * Converts time value to granule.
 * @param timeUs Time in milliseconds.
 * @return The granule value.
 */
protected long convertTimeToGranule(long timeUs){
  return (sampleRate * timeUs) / C.MICROS_PER_SECOND;
}
