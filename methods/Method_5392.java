/** 
 * Attempts to seek to the specified position within the sample queues.
 * @param positionUs The seek position in microseconds.
 * @return Whether the in-buffer seek was successful.
 */
private boolean seekInsideBufferUs(long positionUs){
  int sampleQueueCount=sampleQueues.length;
  for (int i=0; i < sampleQueueCount; i++) {
    SampleQueue sampleQueue=sampleQueues[i];
    sampleQueue.rewind();
    boolean seekInsideQueue=sampleQueue.advanceTo(positionUs,true,false) != SampleQueue.ADVANCE_FAILED;
    if (!seekInsideQueue && (sampleQueueIsAudioVideoFlags[i] || !haveAudioVideoSampleQueues)) {
      return false;
    }
  }
  return true;
}
