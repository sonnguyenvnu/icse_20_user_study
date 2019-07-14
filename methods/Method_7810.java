public void mergeExcludeResults(){
  if (delegate == null) {
    return;
  }
  SparseArray<TLRPC.User> ignoreUsers=delegate.getExcludeUsers();
  if (ignoreUsers == null) {
    return;
  }
  for (int a=0, size=ignoreUsers.size(); a < size; a++) {
    TLRPC.User u=(TLRPC.User)globalSearchMap.get(ignoreUsers.keyAt(a));
    if (u != null) {
      globalSearch.remove(u);
      localServerSearch.remove(u);
      globalSearchMap.remove(u.id);
    }
  }
}
