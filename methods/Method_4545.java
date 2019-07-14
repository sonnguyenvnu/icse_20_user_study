/** 
 * Obtains the index of the chunk corresponding to a given time.
 * @param timeUs The time, in microseconds.
 * @return The index of the corresponding chunk.
 */
public int getChunkIndex(long timeUs){
  return Util.binarySearchFloor(timesUs,timeUs,true,true);
}
