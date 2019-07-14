private void updateSearchResults(final ArrayList<TLObject> result,final ArrayList<CharSequence> names,final ArrayList<TLRPC.User> encUsers,final int searchId){
  AndroidUtilities.runOnUIThread(() -> {
    if (searchId != lastSearchId) {
      return;
    }
    searchWas=true;
    for (int a=0; a < result.size(); a++) {
      TLObject obj=result.get(a);
      if (obj instanceof TLRPC.User) {
        TLRPC.User user=(TLRPC.User)obj;
        MessagesController.getInstance(currentAccount).putUser(user,true);
      }
 else       if (obj instanceof TLRPC.Chat) {
        TLRPC.Chat chat=(TLRPC.Chat)obj;
        MessagesController.getInstance(currentAccount).putChat(chat,true);
      }
 else       if (obj instanceof TLRPC.EncryptedChat) {
        TLRPC.EncryptedChat chat=(TLRPC.EncryptedChat)obj;
        MessagesController.getInstance(currentAccount).putEncryptedChat(chat,true);
      }
    }
    MessagesController.getInstance(currentAccount).putUsers(encUsers,true);
    searchResult=result;
    searchResultNames=names;
    searchAdapterHelper.mergeResults(searchResult);
    notifyDataSetChanged();
    if (delegate != null) {
      if (getItemCount() == 0 && (searchRunnable2 != null || searchAdapterHelper.isSearchInProgress())) {
        delegate.searchStateChanged(true);
      }
 else {
        delegate.searchStateChanged(false);
      }
    }
  }
);
}
