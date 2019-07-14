/** 
 * Adds or remove a repetition at a certain timestamp. <p> If there exists a repetition on the list with the given timestamp, the method removes this repetition from the list and returns it. If there are no repetitions with the given timestamp, creates and adds one to the list, then returns it.
 * @param timestamp the timestamp for the timestamp that should be added orremoved.
 * @return the repetition that has been added or removed.
 */
@NonNull public synchronized Repetition toggle(Timestamp timestamp){
  if (habit.isNumerical())   throw new IllegalStateException("habit must NOT be numerical");
  Repetition rep=getByTimestamp(timestamp);
  if (rep != null)   remove(rep);
 else {
    rep=new Repetition(timestamp,Checkmark.CHECKED_EXPLICITLY);
    add(rep);
  }
  habit.invalidateNewerThan(timestamp);
  return rep;
}
