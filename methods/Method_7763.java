public void searchDialogs(String text){
  if (text != null && text.equals(lastSearchText)) {
    return;
  }
  lastSearchText=text;
  if (searchRunnable != null) {
    Utilities.searchQueue.cancelRunnable(searchRunnable);
    searchRunnable=null;
  }
  if (searchRunnable2 != null) {
    AndroidUtilities.cancelRunOnUIThread(searchRunnable2);
    searchRunnable2=null;
  }
  String query;
  if (text != null) {
    query=text.trim();
  }
 else {
    query=null;
  }
  if (TextUtils.isEmpty(query)) {
    searchAdapterHelper.unloadRecentHashtags();
    searchResult.clear();
    searchResultNames.clear();
    searchResultHashtags.clear();
    searchAdapterHelper.mergeResults(null);
    if (needMessagesSearch != 2) {
      searchAdapterHelper.queryServerSearch(null,true,true,true,true,0,0);
    }
    searchWas=false;
    lastSearchId=-1;
    searchMessagesInternal(null);
    notifyDataSetChanged();
  }
 else {
    if (needMessagesSearch != 2 && (query.startsWith("#") && query.length() == 1)) {
      messagesSearchEndReached=true;
      if (searchAdapterHelper.loadRecentHashtags()) {
        searchResultMessages.clear();
        searchResultHashtags.clear();
        ArrayList<SearchAdapterHelper.HashtagObject> hashtags=searchAdapterHelper.getHashtags();
        for (int a=0; a < hashtags.size(); a++) {
          searchResultHashtags.add(hashtags.get(a).hashtag);
        }
        if (delegate != null) {
          delegate.searchStateChanged(false);
        }
      }
 else {
      }
      notifyDataSetChanged();
    }
 else {
      searchResultHashtags.clear();
      notifyDataSetChanged();
    }
    final int searchId=++lastSearchId;
    Utilities.searchQueue.postRunnable(searchRunnable=() -> {
      searchRunnable=null;
      searchDialogsInternal(query,searchId);
      AndroidUtilities.runOnUIThread(searchRunnable2=() -> {
        searchRunnable2=null;
        if (searchId != lastSearchId) {
          return;
        }
        if (needMessagesSearch != 2) {
          searchAdapterHelper.queryServerSearch(query,true,true,true,true,0,0);
        }
        searchMessagesInternal(query);
      }
);
    }
,300);
  }
}
