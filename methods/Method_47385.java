/** 
 * publish progress after calculating the write length
 * @param newPosition the position of byte for file being processed
 */
public synchronized void addWrittenLength(long newPosition){
  long speedRaw=(newPosition - writtenSize);
  this.writtenSize=newPosition;
  progressListener.onProgressed(speedRaw);
}
