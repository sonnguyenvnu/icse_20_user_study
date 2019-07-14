/** 
 * Returns the number of days between this timestamp and the given one. If the other timestamp equals this one, returns zero. If the other timestamp is older than this one, returns a negative number.
 */
public int daysUntil(Timestamp other){
  return (int)((other.unixTime - this.unixTime) / DAY_LENGTH);
}
