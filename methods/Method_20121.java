/** 
 * Prevents any ongoing diff from dispatching results. Returns true if there was an ongoing diff to cancel, false otherwise.
 */
@SuppressWarnings("WeakerAccess") @AnyThread public boolean cancelDiff(){
  return generationTracker.finishMaxGeneration();
}
