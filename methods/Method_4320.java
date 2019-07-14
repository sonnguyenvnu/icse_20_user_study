/** 
 * Returns whether the upstream source is ready.
 */
protected final boolean isSourceReady(){
  return readEndOfStream ? streamIsFinal : stream.isReady();
}
