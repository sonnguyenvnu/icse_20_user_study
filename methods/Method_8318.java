private void openSearchWithText(String text){
  if (!actionBar.isSearchFieldVisible()) {
    avatarContainer.setVisibility(View.GONE);
    headerItem.setVisibility(View.GONE);
    attachItem.setVisibility(View.GONE);
    editTextItem.setVisibility(View.GONE);
    searchItem.setVisibility(View.VISIBLE);
    updateSearchButtons(0,0,-1);
    updateBottomOverlay();
  }
  openSearchKeyboard=text == null;
  searchItem.openSearch(openSearchKeyboard);
  if (text != null) {
    searchItem.setSearchFieldText(text,false);
    DataQuery.getInstance(currentAccount).searchMessagesInChat(text,dialog_id,mergeDialogId,classGuid,0,searchingUserMessages);
  }
  updatePinnedMessageView(true);
}
