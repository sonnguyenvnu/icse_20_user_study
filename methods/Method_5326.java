/** 
 * Attempts to seek to the specified position within the sample queues.
 * @param trackIsAudioVideoFlags Whether each track is audio/video.
 * @param positionUs The seek position in microseconds.
 * @return Whether the in-buffer seek was successful.
 */
private boolean seekInsideBufferUs(boolean[] trackIsAudioVideoFlags,long positionUs){
  int trackCount=sampleQueues.length;
  for (int i=0; i < trackCount; i++) {
    SampleQueue sampleQueue=sampleQueues[i];
    sampleQueue.rewind();
    boolean seekInsideQueue=sampleQueue.advanceTo(positionUs,true,false) != SampleQueue.ADVANCE_FAILED;
    if (!seekInsideQueue && (trackIsAudioVideoFlags[i] || !haveAudioVideoTracks)) {
      return false;
    }
  }
  return true;
}
