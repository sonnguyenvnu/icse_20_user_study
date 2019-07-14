/** 
 * Returns whether it is time to show a new hint or not.
 * @return true if hint should be shown, false otherwise
 */
public boolean shouldShow(){
  Timestamp today=DateUtils.getToday();
  Timestamp lastHintTimestamp=prefs.getLastHintTimestamp();
  return (lastHintTimestamp.isOlderThan(today));
}
