/** 
 * Computes and stores one checkmark for each day, from the first habit repetition to today. If this list is already computed, does nothing.
 */
protected final synchronized void compute(){
  final Timestamp today=DateUtils.getToday();
  Checkmark newest=getNewestComputed();
  if (newest != null && newest.getTimestamp().equals(today))   return;
  invalidateNewerThan(Timestamp.ZERO);
  Repetition oldestRep=habit.getRepetitions().getOldest();
  if (oldestRep == null)   return;
  final Timestamp from=oldestRep.getTimestamp();
  Repetition reps[]=habit.getRepetitions().getByInterval(from,today).toArray(new Repetition[0]);
  if (habit.isNumerical())   computeNumerical(reps);
 else   computeYesNo(reps);
}
