/** 
 * A throwing variant of  {@link #proceed(int)}.
 * @param priority The priority of the task.
 * @throws PriorityTooLowException If the task is not allowed to proceed.
 */
public void proceedOrThrow(int priority) throws PriorityTooLowException {
synchronized (lock) {
    if (highestPriority != priority) {
      throw new PriorityTooLowException(priority,highestPriority);
    }
  }
}
