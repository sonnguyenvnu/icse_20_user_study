/** 
 * Returns whether the habit has a reminder.
 * @return true if habit has reminder, false otherwise
 */
public synchronized boolean hasReminder(){
  return data.reminder != null;
}
