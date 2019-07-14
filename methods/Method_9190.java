public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.updateInterfaces) {
    int mask=(Integer)args[0];
    if ((mask & MessagesController.UPDATE_MASK_AVATAR) != 0 || (mask & MessagesController.UPDATE_MASK_STATUS) != 0) {
      updateAvatarLayout();
    }
  }
}
