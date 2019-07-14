/** 
 * Returns whether samples have been read from media chunk at given index. 
 */
private boolean haveReadFromMediaChunk(int mediaChunkIndex){
  BaseMediaChunk mediaChunk=mediaChunks.get(mediaChunkIndex);
  if (primarySampleQueue.getReadIndex() > mediaChunk.getFirstSampleIndex(0)) {
    return true;
  }
  for (int i=0; i < embeddedSampleQueues.length; i++) {
    if (embeddedSampleQueues[i].getReadIndex() > mediaChunk.getFirstSampleIndex(i + 1)) {
      return true;
    }
  }
  return false;
}
