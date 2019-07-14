/** 
 * Attempts to advance the read position to the sample before or at the specified time.
 * @param timeUs The time to advance to.
 * @param toKeyframe If true then attempts to advance to the keyframe before or at the specifiedtime, rather than to any sample before or at that time.
 * @param allowTimeBeyondBuffer Whether the operation can succeed if {@code timeUs} is beyond theend of the queue, by advancing the read position to the last sample (or keyframe) in the queue.
 * @return The number of samples that were skipped if the operation was successful, which may beequal to 0, or  {@link SampleQueue#ADVANCE_FAILED} if the operation was not successful. Asuccessful advance is one in which the read position was unchanged or advanced, and is now at a sample meeting the specified criteria.
 */
public synchronized int advanceTo(long timeUs,boolean toKeyframe,boolean allowTimeBeyondBuffer){
  int relativeReadIndex=getRelativeIndex(readPosition);
  if (!hasNextSample() || timeUs < timesUs[relativeReadIndex] || (timeUs > largestQueuedTimestampUs && !allowTimeBeyondBuffer)) {
    return SampleQueue.ADVANCE_FAILED;
  }
  int offset=findSampleBefore(relativeReadIndex,length - readPosition,timeUs,toKeyframe);
  if (offset == -1) {
    return SampleQueue.ADVANCE_FAILED;
  }
  readPosition+=offset;
  return offset;
}
