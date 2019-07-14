public void processLoadedBlockedUsers(final SparseIntArray ids,final ArrayList<TLRPC.User> users,final boolean cache){
  AndroidUtilities.runOnUIThread(() -> {
    if (users != null) {
      putUsers(users,cache);
    }
    loadingBlockedUsers=false;
    if (ids.size() == 0 && cache && !UserConfig.getInstance(currentAccount).blockedUsersLoaded) {
      getBlockedUsers(false);
      return;
    }
 else     if (!cache) {
      UserConfig.getInstance(currentAccount).blockedUsersLoaded=true;
      UserConfig.getInstance(currentAccount).saveConfig(false);
    }
    blockedUsers=ids;
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.blockedUsersDidLoad);
  }
);
}
