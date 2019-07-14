/** 
 * Finishes tracking an operation.
 * @param extraDataCounter An optional additional data counter to track along the measurements.Users are free to track any extra value they want (ie: number of analyzed nodes, iterations in a loop, etc.)
 */
static void finishOperation(final long extraDataCounter){
  if (!trackTime) {
    return;
  }
  final Queue<TimerEntry> queue=TIMER_ENTRIES.get();
  final TimerEntry timerEntry=queue.remove();
  TimedResult result=ACCUMULATED_RESULTS.get(timerEntry.operation);
  if (result == null) {
    ACCUMULATED_RESULTS.putIfAbsent(timerEntry.operation,new TimedResult());
    result=ACCUMULATED_RESULTS.get(timerEntry.operation);
  }
  final long delta=result.accumulate(timerEntry,extraDataCounter);
  if (!queue.isEmpty()) {
    queue.peek().inNestedOperationsNanos+=delta;
  }
}
