/** 
 * Starts tracking an operation.
 * @param category The category under which to track the operation.
 * @param label A label to be added to the category. Allows to differentiate measures within a single category.
 * @return The current timed operation being tracked.
 */
public static TimedOperation startOperation(final TimedOperationCategory category,final String label){
  if (!trackTime) {
    return NOOP_TIMED_OPERATION;
  }
  TIMER_ENTRIES.get().add(new TimerEntry(category,label));
  return new TimedOperationImpl();
}
