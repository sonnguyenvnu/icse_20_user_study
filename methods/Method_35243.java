private void ensurePagesSaved(){
  while (savedPages.size() > maxPagesToStateSave) {
    int positionToRemove=savedPageHistory.remove(0);
    savedPages.remove(positionToRemove);
  }
}
