public void processLoadedUserPhotos(final TLRPC.photos_Photos res,final int did,final int count,final long max_id,final boolean fromCache,final int classGuid){
  if (!fromCache) {
    MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,null,true,true);
    MessagesStorage.getInstance(currentAccount).putDialogPhotos(did,res);
  }
 else   if (res == null || res.photos.isEmpty()) {
    loadDialogPhotos(did,count,max_id,false,classGuid);
    return;
  }
  AndroidUtilities.runOnUIThread(() -> {
    putUsers(res.users,fromCache);
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogPhotosLoaded,did,count,fromCache,classGuid,res.photos);
  }
);
}
