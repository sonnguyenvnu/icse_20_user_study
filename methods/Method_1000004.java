/** 
 * Flush internal buffers, so that less than a block of data may at most be upheld.
 * @return the number of bytes still unprocessed after the flush
 */
protected final int flush(){
  return inputLen;
}
