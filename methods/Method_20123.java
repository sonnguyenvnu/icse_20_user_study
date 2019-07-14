/** 
 * Set the current list without performing any diffing. Cancels any diff in progress. <p> This can be used if you notified a change to the adapter manually and need this list to be synced.
 */
@AnyThread public synchronized boolean forceListOverride(@Nullable List<EpoxyModel<?>> newList){
  final boolean interruptedDiff=cancelDiff();
  int generation=generationTracker.incrementAndGetNextScheduled();
  tryLatchList(newList,generation);
  return interruptedDiff;
}
