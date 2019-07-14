@Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.didSetTwoStepPassword) {
    if (args != null && args.length > 0 && args[0] != null) {
      currentPasswordHash=(byte[])args[0];
      if (closeAfterSet) {
        String email=(String)args[4];
        if (TextUtils.isEmpty(email) && closeAfterSet) {
          removeSelfFromStack();
        }
      }
    }
    loadPasswordInfo(false);
    updateRows();
  }
}
