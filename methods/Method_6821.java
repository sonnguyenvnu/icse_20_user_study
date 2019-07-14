public void putUsers(ArrayList<TLRPC.User> users,boolean fromCache){
  if (users == null || users.isEmpty()) {
    return;
  }
  boolean updateStatus=false;
  int count=users.size();
  for (int a=0; a < count; a++) {
    TLRPC.User user=users.get(a);
    if (putUser(user,fromCache)) {
      updateStatus=true;
    }
  }
  if (updateStatus) {
    AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_STATUS));
  }
}
