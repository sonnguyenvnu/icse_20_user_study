/** 
 * Adjusts a frame release timestamp. Must be called from the playback thread.
 * @param framePresentationTimeUs The frame's presentation time, in microseconds.
 * @param unadjustedReleaseTimeNs The frame's unadjusted release time, in nanoseconds and inthe same time base as  {@link System#nanoTime()}.
 * @return The adjusted frame release timestamp, in nanoseconds and in the same time base as{@link System#nanoTime()}.
 */
public long adjustReleaseTime(long framePresentationTimeUs,long unadjustedReleaseTimeNs){
  long framePresentationTimeNs=framePresentationTimeUs * 1000;
  long adjustedFrameTimeNs=framePresentationTimeNs;
  long adjustedReleaseTimeNs=unadjustedReleaseTimeNs;
  if (haveSync) {
    if (framePresentationTimeUs != lastFramePresentationTimeUs) {
      frameCount++;
      adjustedLastFrameTimeNs=pendingAdjustedFrameTimeNs;
    }
    if (frameCount >= MIN_FRAMES_FOR_ADJUSTMENT) {
      long averageFrameDurationNs=(framePresentationTimeNs - syncFramePresentationTimeNs) / frameCount;
      long candidateAdjustedFrameTimeNs=adjustedLastFrameTimeNs + averageFrameDurationNs;
      if (isDriftTooLarge(candidateAdjustedFrameTimeNs,unadjustedReleaseTimeNs)) {
        haveSync=false;
      }
 else {
        adjustedFrameTimeNs=candidateAdjustedFrameTimeNs;
        adjustedReleaseTimeNs=syncUnadjustedReleaseTimeNs + adjustedFrameTimeNs - syncFramePresentationTimeNs;
      }
    }
 else {
      if (isDriftTooLarge(framePresentationTimeNs,unadjustedReleaseTimeNs)) {
        haveSync=false;
      }
    }
  }
  if (!haveSync) {
    syncFramePresentationTimeNs=framePresentationTimeNs;
    syncUnadjustedReleaseTimeNs=unadjustedReleaseTimeNs;
    frameCount=0;
    haveSync=true;
  }
  lastFramePresentationTimeUs=framePresentationTimeUs;
  pendingAdjustedFrameTimeNs=adjustedFrameTimeNs;
  if (vsyncSampler == null || vsyncDurationNs == C.TIME_UNSET) {
    return adjustedReleaseTimeNs;
  }
  long sampledVsyncTimeNs=vsyncSampler.sampledVsyncTimeNs;
  if (sampledVsyncTimeNs == C.TIME_UNSET) {
    return adjustedReleaseTimeNs;
  }
  long snappedTimeNs=closestVsync(adjustedReleaseTimeNs,sampledVsyncTimeNs,vsyncDurationNs);
  return snappedTimeNs - vsyncOffsetNs;
}
