/** 
 * @return True if a diff operation is in progress.
 */
@SuppressWarnings("WeakerAccess") @AnyThread public boolean isDiffInProgress(){
  return generationTracker.hasUnfinishedGeneration();
}
