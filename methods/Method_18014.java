@VisibleForTesting @GuardedBy("this") boolean hasReferencesToNodes(){
  return !mBindings.isEmpty() || !mSortedNodes.isEmpty() || !mNodeStates.isEmpty();
}
