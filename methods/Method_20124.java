/** 
 * Marks the generation as done, and updates the list if the generation is the most recent.
 * @return True if the given generation is the most recent, in which case the given list wasset. False if the generation is old and the list was ignored.
 */
@AnyThread private synchronized boolean tryLatchList(@Nullable List<? extends EpoxyModel<?>> newList,int runGeneration){
  if (generationTracker.finishGeneration(runGeneration)) {
    list=newList;
    if (newList == null) {
      readOnlyList=Collections.emptyList();
    }
 else {
      readOnlyList=Collections.unmodifiableList(newList);
    }
    return true;
  }
  return false;
}
