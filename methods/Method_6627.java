public static String formatUserStatus(int currentAccount,TLRPC.User user,boolean[] isOnline){
  if (user != null && user.status != null && user.status.expires == 0) {
    if (user.status instanceof TLRPC.TL_userStatusRecently) {
      user.status.expires=-100;
    }
 else     if (user.status instanceof TLRPC.TL_userStatusLastWeek) {
      user.status.expires=-101;
    }
 else     if (user.status instanceof TLRPC.TL_userStatusLastMonth) {
      user.status.expires=-102;
    }
  }
  if (user != null && user.status != null && user.status.expires <= 0) {
    if (MessagesController.getInstance(currentAccount).onlinePrivacy.containsKey(user.id)) {
      if (isOnline != null) {
        isOnline[0]=true;
      }
      return getString("Online",R.string.Online);
    }
  }
  if (user == null || user.status == null || user.status.expires == 0 || UserObject.isDeleted(user) || user instanceof TLRPC.TL_userEmpty) {
    return getString("ALongTimeAgo",R.string.ALongTimeAgo);
  }
 else {
    int currentTime=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
    if (user.status.expires > currentTime) {
      if (isOnline != null) {
        isOnline[0]=true;
      }
      return getString("Online",R.string.Online);
    }
 else {
      if (user.status.expires == -1) {
        return getString("Invisible",R.string.Invisible);
      }
 else       if (user.status.expires == -100) {
        return getString("Lately",R.string.Lately);
      }
 else       if (user.status.expires == -101) {
        return getString("WithinAWeek",R.string.WithinAWeek);
      }
 else       if (user.status.expires == -102) {
        return getString("WithinAMonth",R.string.WithinAMonth);
      }
 else {
        return formatDateOnline(user.status.expires);
      }
    }
  }
}
