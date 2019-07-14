/** 
 * Resets this stream and uses the given output stream for writing. This stream must be closed before resetting.
 * @param out New output stream to be used for writing.
 * @throws IllegalStateException If the stream isn't closed.
 */
public void reset(OutputStream out){
  Assertions.checkState(closed);
  this.out=out;
  count=0;
  closed=false;
}
