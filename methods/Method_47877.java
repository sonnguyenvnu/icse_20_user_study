/** 
 * Returns true if the list contains a repetition that has the given timestamp.
 * @param timestamp the timestamp to find.
 * @return true if list contains repetition with given timestamp, falseotherwise.
 */
public boolean containsTimestamp(Timestamp timestamp){
  return (getByTimestamp(timestamp) != null);
}
