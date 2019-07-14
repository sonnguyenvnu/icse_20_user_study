/** 
 * Finds the place where we should start when recomputing the streaks.
 * @return
 */
@Nullable protected Timestamp findBeginning(){
  Streak newestStreak=getNewestComputed();
  if (newestStreak != null)   return newestStreak.getStart();
  Repetition oldestRep=habit.getRepetitions().getOldest();
  if (oldestRep != null)   return oldestRep.getTimestamp();
  return null;
}
