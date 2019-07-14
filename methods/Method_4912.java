/** 
 * Converts renderer time to the respective time relative to the start of the period using  {@link #getRendererOffset()}, in microseconds.
 */
public long toPeriodTime(long rendererTimeUs){
  return rendererTimeUs - getRendererOffset();
}
