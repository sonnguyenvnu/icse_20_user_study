/** 
 * Given a period index into an old timeline, finds the first subsequent period that also exists in a new timeline. The uid of this period in the new timeline is returned.
 * @param oldPeriodUid The index of the period in the old timeline.
 * @param oldTimeline The old timeline.
 * @param newTimeline The new timeline.
 * @return The uid in the new timeline of the first subsequent period, or null if no such periodwas found.
 */
private @Nullable Object resolveSubsequentPeriod(Object oldPeriodUid,Timeline oldTimeline,Timeline newTimeline){
  int oldPeriodIndex=oldTimeline.getIndexOfPeriod(oldPeriodUid);
  int newPeriodIndex=C.INDEX_UNSET;
  int maxIterations=oldTimeline.getPeriodCount();
  for (int i=0; i < maxIterations && newPeriodIndex == C.INDEX_UNSET; i++) {
    oldPeriodIndex=oldTimeline.getNextPeriodIndex(oldPeriodIndex,period,window,repeatMode,shuffleModeEnabled);
    if (oldPeriodIndex == C.INDEX_UNSET) {
      break;
    }
    newPeriodIndex=newTimeline.getIndexOfPeriod(oldTimeline.getUidOfPeriod(oldPeriodIndex));
  }
  return newPeriodIndex == C.INDEX_UNSET ? null : newTimeline.getUidOfPeriod(newPeriodIndex);
}
