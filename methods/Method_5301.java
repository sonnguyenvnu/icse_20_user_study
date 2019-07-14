/** 
 * Overrides the default prepare position at which to prepare the media period. This value is only used if called before  {@link #createPeriod(MediaPeriodId)}.
 * @param preparePositionUs The default prepare position to use, in microseconds.
 */
public void overridePreparePositionUs(long preparePositionUs){
  preparePositionOverrideUs=preparePositionUs;
}
