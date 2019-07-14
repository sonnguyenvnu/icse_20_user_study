/** 
 * Enqueues a new media period based on the specified information as the new loading media period, and returns it.
 * @param rendererCapabilities The renderer capabilities.
 * @param trackSelector The track selector.
 * @param allocator The allocator.
 * @param mediaSource The media source that produced the media period.
 * @param info Information used to identify this media period in its timeline period.
 */
public MediaPeriod enqueueNextMediaPeriod(RendererCapabilities[] rendererCapabilities,TrackSelector trackSelector,Allocator allocator,MediaSource mediaSource,MediaPeriodInfo info){
  long rendererPositionOffsetUs=loading == null ? info.startPositionUs : (loading.getRendererOffset() + loading.info.durationUs);
  MediaPeriodHolder newPeriodHolder=new MediaPeriodHolder(rendererCapabilities,rendererPositionOffsetUs,trackSelector,allocator,mediaSource,info);
  if (loading != null) {
    Assertions.checkState(hasPlayingPeriod());
    loading.setNext(newPeriodHolder);
  }
  oldFrontPeriodUid=null;
  loading=newPeriodHolder;
  length++;
  return newPeriodHolder.mediaPeriod;
}
