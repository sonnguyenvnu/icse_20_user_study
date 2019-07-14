/** 
 * Notify analytics collector that a seek operation will start. Should be called before the player adjusts its state and position to the seek.
 */
public final void notifySeekStarted(){
  if (!mediaPeriodQueueTracker.isSeeking()) {
    EventTime eventTime=generatePlayingMediaPeriodEventTime();
    mediaPeriodQueueTracker.onSeekStarted();
    for (    AnalyticsListener listener : listeners) {
      listener.onSeekStarted(eventTime);
    }
  }
}
