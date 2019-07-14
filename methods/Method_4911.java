/** 
 * Converts time relative to the start of the period to the respective renderer time using  {@link #getRendererOffset()}, in microseconds.
 */
public long toRendererTime(long periodTimeUs){
  return periodTimeUs + getRendererOffset();
}
