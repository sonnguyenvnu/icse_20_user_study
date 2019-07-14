private void updateNotification(boolean post){
  if (builder == null) {
    return;
  }
  String param;
  ArrayList<LocationController.SharingLocationInfo> infos=getInfos();
  if (infos.size() == 1) {
    LocationController.SharingLocationInfo info=infos.get(0);
    int lower_id=(int)info.messageObject.getDialogId();
    int currentAccount=info.messageObject.currentAccount;
    if (lower_id > 0) {
      TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(lower_id);
      param=UserObject.getFirstName(user);
    }
 else {
      TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-lower_id);
      if (chat != null) {
        param=chat.title;
      }
 else {
        param="";
      }
    }
  }
 else {
    param=LocaleController.formatPluralString("Chats",infos.size());
  }
  String str=String.format(LocaleController.getString("AttachLiveLocationIsSharing",R.string.AttachLiveLocationIsSharing),LocaleController.getString("AttachLiveLocation",R.string.AttachLiveLocation),param);
  builder.setTicker(str);
  builder.setContentText(str);
  if (post) {
    NotificationManagerCompat.from(ApplicationLoader.applicationContext).notify(6,builder.build());
  }
}
