private void setRecentSearch(ArrayList<RecentSearchObject> arrayList,LongSparseArray<RecentSearchObject> hashMap){
  recentSearchObjects=arrayList;
  recentSearchObjectsById=hashMap;
  for (int a=0; a < recentSearchObjects.size(); a++) {
    RecentSearchObject recentSearchObject=recentSearchObjects.get(a);
    if (recentSearchObject.object instanceof TLRPC.User) {
      MessagesController.getInstance(currentAccount).putUser((TLRPC.User)recentSearchObject.object,true);
    }
 else     if (recentSearchObject.object instanceof TLRPC.Chat) {
      MessagesController.getInstance(currentAccount).putChat((TLRPC.Chat)recentSearchObject.object,true);
    }
 else     if (recentSearchObject.object instanceof TLRPC.EncryptedChat) {
      MessagesController.getInstance(currentAccount).putEncryptedChat((TLRPC.EncryptedChat)recentSearchObject.object,true);
    }
  }
  notifyDataSetChanged();
}
