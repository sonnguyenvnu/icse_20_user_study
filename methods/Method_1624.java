/** 
 * Calls  {@code onCancellationRequested} on each element of the list. Does nothing if list == null
 */
public static void callOnCancellationRequested(@Nullable List<ProducerContextCallbacks> callbacks){
  if (callbacks == null) {
    return;
  }
  for (  ProducerContextCallbacks callback : callbacks) {
    callback.onCancellationRequested();
  }
}
