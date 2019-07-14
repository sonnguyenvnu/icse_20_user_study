public void mergeResults(ArrayList<TLObject> localResults){
  localSearchResults=localResults;
  if (globalSearchMap.size() == 0 || localResults == null) {
    return;
  }
  int count=localResults.size();
  for (int a=0; a < count; a++) {
    TLObject obj=localResults.get(a);
    if (obj instanceof TLRPC.User) {
      TLRPC.User user=(TLRPC.User)obj;
      TLRPC.User u=(TLRPC.User)globalSearchMap.get(user.id);
      if (u != null) {
        globalSearch.remove(u);
        localServerSearch.remove(u);
        globalSearchMap.remove(u.id);
      }
      TLObject participant=groupSearchMap.get(user.id);
      if (participant != null) {
        groupSearch.remove(participant);
        groupSearchMap.remove(user.id);
      }
    }
 else     if (obj instanceof TLRPC.Chat) {
      TLRPC.Chat chat=(TLRPC.Chat)obj;
      TLRPC.Chat c=(TLRPC.Chat)globalSearchMap.get(-chat.id);
      if (c != null) {
        globalSearch.remove(c);
        localServerSearch.remove(c);
        globalSearchMap.remove(-c.id);
      }
    }
  }
}
