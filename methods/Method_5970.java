/** 
 * A non-blocking variant of  {@link #proceed(int)}.
 * @param priority The priority of the task.
 * @return Whether the task is allowed to proceed.
 */
public boolean proceedNonBlocking(int priority){
synchronized (lock) {
    return highestPriority == priority;
  }
}
