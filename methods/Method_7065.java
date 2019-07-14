private void loadChildrenImpl(final String parentMediaId,final Result<List<MediaBrowser.MediaItem>> result){
  List<MediaBrowser.MediaItem> mediaItems=new ArrayList<>();
  if (MEDIA_ID_ROOT.equals(parentMediaId)) {
    for (int a=0; a < dialogs.size(); a++) {
      int did=dialogs.get(a);
      MediaDescription.Builder builder=new MediaDescription.Builder().setMediaId("__CHAT_" + did);
      TLRPC.FileLocation avatar=null;
      if (did > 0) {
        TLRPC.User user=users.get(did);
        if (user != null) {
          builder.setTitle(ContactsController.formatName(user.first_name,user.last_name));
          if (user.photo != null && !(user.photo.photo_small instanceof TLRPC.TL_fileLocationUnavailable)) {
            avatar=user.photo.photo_small;
          }
        }
 else {
          builder.setTitle("DELETED USER");
        }
      }
 else {
        TLRPC.Chat chat=chats.get(-did);
        if (chat != null) {
          builder.setTitle(chat.title);
          if (chat.photo != null && !(chat.photo.photo_small instanceof TLRPC.TL_fileLocationUnavailable)) {
            avatar=chat.photo.photo_small;
          }
        }
 else {
          builder.setTitle("DELETED CHAT");
        }
      }
      Bitmap bitmap=null;
      if (avatar != null) {
        bitmap=createRoundBitmap(FileLoader.getPathToAttach(avatar,true));
        if (bitmap != null) {
          builder.setIconBitmap(bitmap);
        }
      }
      if (avatar == null || bitmap == null) {
        builder.setIconUri(Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/drawable/contact_blue"));
      }
      mediaItems.add(new MediaBrowser.MediaItem(builder.build(),MediaBrowser.MediaItem.FLAG_BROWSABLE));
    }
  }
 else   if (parentMediaId != null && parentMediaId.startsWith("__CHAT_")) {
    int did=0;
    try {
      did=Integer.parseInt(parentMediaId.replace("__CHAT_",""));
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    ArrayList<MessageObject> arrayList=musicObjects.get(did);
    if (arrayList != null) {
      for (int a=0; a < arrayList.size(); a++) {
        MessageObject messageObject=arrayList.get(a);
        MediaDescription.Builder builder=new MediaDescription.Builder().setMediaId(did + "_" + a);
        builder.setTitle(messageObject.getMusicTitle());
        builder.setSubtitle(messageObject.getMusicAuthor());
        mediaItems.add(new MediaBrowser.MediaItem(builder.build(),MediaBrowser.MediaItem.FLAG_PLAYABLE));
      }
    }
  }
  result.sendResult(mediaItems);
}
