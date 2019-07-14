/** 
 * Returns the value of the score for a given day. <p> If the timestamp given happens before the first repetition of the habit then returns zero.
 * @param timestamp the timestamp of a day
 * @return score value for that day
 */
public final synchronized double getValue(Timestamp timestamp){
  compute(timestamp,timestamp);
  Score s=getComputedByTimestamp(timestamp);
  if (s == null)   throw new IllegalStateException();
  return s.getValue();
}
