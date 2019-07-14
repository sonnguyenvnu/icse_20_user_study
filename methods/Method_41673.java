/** 
 * Set the name of the  {@link Calendar} that should be applied to thisTrigger's schedule.
 * @param calName the name of the Calendar to reference.
 * @return the updated TriggerBuilder
 * @see Calendar
 * @see Trigger#getCalendarName()
 */
public TriggerBuilder<T> modifiedByCalendar(String calName){
  this.calendarName=calName;
  return this;
}
