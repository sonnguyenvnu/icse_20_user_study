/** 
 * Peeks the source id of the next sample to be read, or the current upstream source id if the queue is empty or if the read position is at the end of the queue.
 * @return The source id.
 */
public int peekSourceId(){
  int relativeReadIndex=getRelativeIndex(readPosition);
  return hasNextSample() ? sourceIds[relativeReadIndex] : upstreamSourceId;
}
