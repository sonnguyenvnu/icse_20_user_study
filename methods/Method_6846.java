public void deleteUserPhoto(TLRPC.InputPhoto photo){
  if (photo == null) {
    TLRPC.TL_photos_updateProfilePhoto req=new TLRPC.TL_photos_updateProfilePhoto();
    req.id=new TLRPC.TL_inputPhotoEmpty();
    UserConfig.getInstance(currentAccount).getCurrentUser().photo=new TLRPC.TL_userProfilePhotoEmpty();
    TLRPC.User user=getUser(UserConfig.getInstance(currentAccount).getClientUserId());
    if (user == null) {
      user=UserConfig.getInstance(currentAccount).getCurrentUser();
    }
    if (user == null) {
      return;
    }
    user.photo=UserConfig.getInstance(currentAccount).getCurrentUser().photo;
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.mainUserInfoChanged);
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_ALL);
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (error == null) {
        TLRPC.User user1=getUser(UserConfig.getInstance(currentAccount).getClientUserId());
        if (user1 == null) {
          user1=UserConfig.getInstance(currentAccount).getCurrentUser();
          putUser(user1,false);
        }
 else {
          UserConfig.getInstance(currentAccount).setCurrentUser(user1);
        }
        if (user1 == null) {
          return;
        }
        MessagesStorage.getInstance(currentAccount).clearUserPhotos(user1.id);
        ArrayList<TLRPC.User> users=new ArrayList<>();
        users.add(user1);
        MessagesStorage.getInstance(currentAccount).putUsersAndChats(users,null,false,true);
        user1.photo=(TLRPC.UserProfilePhoto)response;
        AndroidUtilities.runOnUIThread(() -> {
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.mainUserInfoChanged);
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_ALL);
          UserConfig.getInstance(currentAccount).saveConfig(true);
        }
);
      }
    }
);
  }
 else {
    TLRPC.TL_photos_deletePhotos req=new TLRPC.TL_photos_deletePhotos();
    req.id.add(photo);
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    }
);
  }
}
