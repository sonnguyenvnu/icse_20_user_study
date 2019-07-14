/** 
 * Gets the overall timeframe of the selected habits. The timeframe is an array containing the oldest timestamp among the habits and the newest timestamp among the habits. Both timestamps are in milliseconds.
 * @return the timeframe containing the oldest timestamp and the newest timestamp
 */
private Timestamp[] getTimeframe(){
  Timestamp oldest=Timestamp.ZERO.plus(1000000);
  Timestamp newest=Timestamp.ZERO;
  for (  Habit h : selectedHabits) {
    if (h.getRepetitions().getOldest() == null || h.getRepetitions().getNewest() == null)     continue;
    Timestamp currOld=h.getRepetitions().getOldest().getTimestamp();
    Timestamp currNew=h.getRepetitions().getNewest().getTimestamp();
    oldest=currOld.isOlderThan(oldest) ? oldest : currOld;
    newest=currNew.isNewerThan(newest) ? newest : currNew;
  }
  return new Timestamp[]{oldest,newest};
}
