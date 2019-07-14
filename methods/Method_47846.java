/** 
 * Clears the reminder for a habit.
 */
public synchronized void clearReminder(){
  data.reminder=null;
  observable.notifyListeners();
}
