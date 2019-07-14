/** 
 * Calls  {@code onIsPrefetchChanged} on each element of the list. Does nothing if list == null
 */
public static void callOnIsPrefetchChanged(@Nullable List<ProducerContextCallbacks> callbacks){
  if (callbacks == null) {
    return;
  }
  for (  ProducerContextCallbacks callback : callbacks) {
    callback.onIsPrefetchChanged();
  }
}
