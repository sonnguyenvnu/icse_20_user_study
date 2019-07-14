public boolean isRecentSearchDisplayed(){
  return needMessagesSearch != 2 && !searchWas && (!recentSearchObjects.isEmpty() || !DataQuery.getInstance(currentAccount).hints.isEmpty());
}
