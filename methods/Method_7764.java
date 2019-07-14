public boolean isGlobalSearch(int i){
  if (isRecentSearchDisplayed()) {
    return false;
  }
  if (!searchResultHashtags.isEmpty()) {
    return false;
  }
  ArrayList<TLObject> globalSearch=searchAdapterHelper.getGlobalSearch();
  ArrayList<TLObject> localServerSearch=searchAdapterHelper.getLocalServerSearch();
  int localCount=searchResult.size();
  int localServerCount=localServerSearch.size();
  int globalCount=globalSearch.isEmpty() ? 0 : globalSearch.size() + 1;
  int messagesCount=searchResultMessages.isEmpty() ? 0 : searchResultMessages.size() + 1;
  if (i >= 0 && i < localCount) {
    return false;
  }
 else   if (i >= localCount && i < localServerCount + localCount) {
    return false;
  }
 else   if (i > localCount + localServerCount && i < globalCount + localCount + localServerCount) {
    return true;
  }
 else   if (i > globalCount + localCount + localServerCount && i < globalCount + localCount + messagesCount + localServerCount) {
    return false;
  }
  return false;
}
