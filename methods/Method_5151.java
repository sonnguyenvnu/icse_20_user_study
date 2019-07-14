/** 
 * Updates the clipping start/end times for this period, in microseconds.
 * @param startUs The clipping start time, in microseconds.
 * @param endUs The clipping end time, in microseconds, or {@link C#TIME_END_OF_SOURCE} toindicate the end of the period.
 */
public void updateClipping(long startUs,long endUs){
  this.startUs=startUs;
  this.endUs=endUs;
}
