/** 
 * Computes and stores one score for each day inside the given interval. <p> Scores that have already been computed are skipped, therefore there is no harm in calling this function more times, or with larger intervals, than strictly needed. The endpoints of the interval are included. <p> This method assumes the list of computed scores has no holes. That is, if there is a score computed at time t1 and another at time t2, then every score between t1 and t2 is also computed.
 * @param from timestamp of the beginning of the interval
 * @param to   timestamp of the end of the time interval
 */
protected synchronized void compute(@NonNull Timestamp from,@NonNull Timestamp to){
  Score newest=getNewestComputed();
  Score oldest=getOldestComputed();
  if (newest == null) {
    Repetition oldestRep=habit.getRepetitions().getOldest();
    if (oldestRep != null)     from=Timestamp.oldest(from,oldestRep.getTimestamp());
    forceRecompute(from,to,0);
  }
 else {
    if (oldest == null)     throw new IllegalStateException();
    forceRecompute(from,oldest.getTimestamp().minus(1),0);
    forceRecompute(newest.getTimestamp().plus(1),to,newest.getValue());
  }
}
