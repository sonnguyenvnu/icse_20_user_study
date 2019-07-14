@Override public void didUploadPhoto(final TLRPC.InputFile file,final TLRPC.PhotoSize bigSize,final TLRPC.PhotoSize smallSize){
  AndroidUtilities.runOnUIThread(() -> {
    if (file != null) {
      TLRPC.TL_photos_uploadProfilePhoto req=new TLRPC.TL_photos_uploadProfilePhoto();
      req.file=file;
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
        if (error == null) {
          TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(UserConfig.getInstance(currentAccount).getClientUserId());
          if (user == null) {
            user=UserConfig.getInstance(currentAccount).getCurrentUser();
            if (user == null) {
              return;
            }
            MessagesController.getInstance(currentAccount).putUser(user,false);
          }
 else {
            UserConfig.getInstance(currentAccount).setCurrentUser(user);
          }
          TLRPC.TL_photos_photo photo=(TLRPC.TL_photos_photo)response;
          ArrayList<TLRPC.PhotoSize> sizes=photo.photo.sizes;
          TLRPC.PhotoSize small=FileLoader.getClosestPhotoSizeWithSize(sizes,150);
          TLRPC.PhotoSize big=FileLoader.getClosestPhotoSizeWithSize(sizes,800);
          user.photo=new TLRPC.TL_userProfilePhoto();
          user.photo.photo_id=photo.photo.id;
          if (small != null) {
            user.photo.photo_small=small.location;
          }
          if (big != null) {
            user.photo.photo_big=big.location;
          }
 else           if (small != null) {
            user.photo.photo_small=small.location;
          }
          if (photo != null) {
            if (small != null && avatar != null) {
              File destFile=FileLoader.getPathToAttach(small,true);
              File src=FileLoader.getPathToAttach(avatar,true);
              src.renameTo(destFile);
              String oldKey=avatar.volume_id + "_" + avatar.local_id + "@50_50";
              String newKey=small.location.volume_id + "_" + small.location.local_id + "@50_50";
              ImageLoader.getInstance().replaceImageInCache(oldKey,newKey,ImageLocation.getForUser(user,false),true);
            }
            if (big != null && avatarBig != null) {
              File destFile=FileLoader.getPathToAttach(big,true);
              File src=FileLoader.getPathToAttach(avatarBig,true);
              src.renameTo(destFile);
            }
          }
          MessagesStorage.getInstance(currentAccount).clearUserPhotos(user.id);
          ArrayList<TLRPC.User> users=new ArrayList<>();
          users.add(user);
          MessagesStorage.getInstance(currentAccount).putUsersAndChats(users,null,false,true);
        }
        AndroidUtilities.runOnUIThread(() -> {
          avatar=null;
          avatarBig=null;
          updateUserData();
          showAvatarProgress(false,true);
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,MessagesController.UPDATE_MASK_ALL);
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.mainUserInfoChanged);
          UserConfig.getInstance(currentAccount).saveConfig(true);
        }
);
      }
);
    }
 else {
      avatar=smallSize.location;
      avatarBig=bigSize.location;
      avatarImage.setImage(ImageLocation.getForLocal(avatar),"50_50",avatarDrawable,null);
      showAvatarProgress(true,false);
    }
  }
);
}
