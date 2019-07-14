/** 
 * Clears the queue.
 * @param keepFrontPeriodUid Whether the queue should keep the id of the media period in the frontof queue (typically the playing one) for later reuse.
 */
public void clear(boolean keepFrontPeriodUid){
  MediaPeriodHolder front=getFrontPeriod();
  if (front != null) {
    oldFrontPeriodUid=keepFrontPeriodUid ? front.uid : null;
    oldFrontPeriodWindowSequenceNumber=front.info.id.windowSequenceNumber;
    front.release();
    removeAfter(front);
  }
 else   if (!keepFrontPeriodUid) {
    oldFrontPeriodUid=null;
  }
  playing=null;
  loading=null;
  reading=null;
  length=0;
}
