/** 
 * Returns true if we have finished reading the backing EntityStream and all buffered bytes have been read.
 */
private boolean done(){
  return _closed || (_readFinished && _buffer == null && _buffers.isEmpty());
}
