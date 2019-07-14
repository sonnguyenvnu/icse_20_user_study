/** 
 * Returns whether a sample is available to be read.
 */
public synchronized boolean hasNextSample(){
  return readPosition != length;
}
