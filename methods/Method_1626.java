/** 
 * Calls  {@code onIsIntermediateResultExpected} on each element of the list. Does nothing iflist == null
 */
public static void callOnIsIntermediateResultExpectedChanged(@Nullable List<ProducerContextCallbacks> callbacks){
  if (callbacks == null) {
    return;
  }
  for (  ProducerContextCallbacks callback : callbacks) {
    callback.onIsIntermediateResultExpectedChanged();
  }
}
