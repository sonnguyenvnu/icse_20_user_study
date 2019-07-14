/** 
 * Resets the analytics collector for a new media source. Should be called before the player is prepared with a new media source.
 */
public final void resetForNewMediaSource(){
  List<MediaPeriodInfo> mediaPeriodInfos=new ArrayList<>(mediaPeriodQueueTracker.mediaPeriodInfoQueue);
  for (  MediaPeriodInfo mediaPeriodInfo : mediaPeriodInfos) {
    onMediaPeriodReleased(mediaPeriodInfo.windowIndex,mediaPeriodInfo.mediaPeriodId);
  }
}
