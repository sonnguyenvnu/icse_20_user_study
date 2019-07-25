/** 
 * Returns a quantiles UpdateDoublesSketch with the current configuration of this builder and the specified backing destination Memory store.
 * @param dstMem destination memory for use by the sketch
 * @return an UpdateDoublesSketch
 */
public UpdateDoublesSketch build(final WritableMemory dstMem){
  return DirectUpdateDoublesSketch.newInstance(bK,dstMem);
}
