/** 
 * Returns a copy of this datetime with the time set to the start of the day. <p> The time will normally be midnight, as that is the earliest time on any given day. However, in some time zones when Daylight Savings Time starts, there is no midnight because time jumps from 11:59 to 01:00. This method handles that situation by returning 01:00 on that date. <p> This instance is immutable and unaffected by this method call.
 * @return a copy of this datetime with the time set to the start of the day, not null
 */
public DateTime withTimeAtStartOfDay(){
  return toLocalDate().toDateTimeAtStartOfDay(getZone());
}
