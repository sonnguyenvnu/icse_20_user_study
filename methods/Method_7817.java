private void searchEmojiByKeyword(){
  String[] newLanguage=AndroidUtilities.getCurrentKeyboardLanguage();
  if (!Arrays.equals(newLanguage,lastSearchKeyboardLanguage)) {
    DataQuery.getInstance(currentAccount).fetchNewEmojiKeywords(newLanguage);
  }
  lastSearchKeyboardLanguage=newLanguage;
  String query=lastSticker;
  cancelEmojiSearch();
  searchRunnable=() -> DataQuery.getInstance(currentAccount).getEmojiSuggestions(lastSearchKeyboardLanguage,query,true,(param,alias) -> {
    if (query.equals(lastSticker)) {
      if (!param.isEmpty()) {
        keywordResults=param;
      }
      notifyDataSetChanged();
      delegate.needChangePanelVisibility(visible=!param.isEmpty());
    }
  }
);
  if (keywordResults == null || keywordResults.isEmpty()) {
    AndroidUtilities.runOnUIThread(searchRunnable,1000);
  }
 else {
    searchRunnable.run();
  }
}
