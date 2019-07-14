/** 
 * Initialize a thread, starting to track it's own time.
 */
public static void initThread(){
  if (!trackTime) {
    return;
  }
  startOperation(TimedOperationCategory.UNACCOUNTED);
}
