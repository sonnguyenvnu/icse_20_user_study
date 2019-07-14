/** 
 * Initializes the wrapper for loading a chunk.
 * @param chunkUid The chunk's uid.
 * @param shouldSpliceIn Whether the samples parsed from the chunk should be spliced into anysamples already queued to the wrapper.
 * @param reusingExtractor Whether the extractor for the chunk has already been used for precedingchunks.
 */
public void init(int chunkUid,boolean shouldSpliceIn,boolean reusingExtractor){
  if (!reusingExtractor) {
    audioSampleQueueMappingDone=false;
    videoSampleQueueMappingDone=false;
  }
  this.chunkUid=chunkUid;
  for (  SampleQueue sampleQueue : sampleQueues) {
    sampleQueue.sourceId(chunkUid);
  }
  if (shouldSpliceIn) {
    for (    SampleQueue sampleQueue : sampleQueues) {
      sampleQueue.splice();
    }
  }
}
