public boolean hasRecentRearch(){
  return !recentSearchObjects.isEmpty() || !DataQuery.getInstance(currentAccount).hints.isEmpty();
}
