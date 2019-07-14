/** 
 * <p> Retrieve the given <code> {@link org.quartz.Trigger}</code>. </p>
 * @param calName The name of the <code>Calendar</code> to be retrieved.
 * @return The desired <code>Calendar</code>, or null if there is nomatch.
 */
public Calendar retrieveCalendar(String calName){
synchronized (lock) {
    Calendar cal=calendarsByName.get(calName);
    if (cal != null)     return (Calendar)cal.clone();
    return null;
  }
}
