public void setException(NotificationsSettingsActivity.NotificationException exception,CharSequence name,boolean divider){
  String text;
  boolean enabled;
  boolean custom=exception.hasCustom;
  int value=exception.notify;
  int delta=exception.muteUntil;
  if (value == 3 && delta != Integer.MAX_VALUE) {
    delta-=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
    if (delta <= 0) {
      if (custom) {
        text=LocaleController.getString("NotificationsCustom",R.string.NotificationsCustom);
      }
 else {
        text=LocaleController.getString("NotificationsUnmuted",R.string.NotificationsUnmuted);
      }
    }
 else     if (delta < 60 * 60) {
      text=LocaleController.formatString("WillUnmuteIn",R.string.WillUnmuteIn,LocaleController.formatPluralString("Minutes",delta / 60));
    }
 else     if (delta < 60 * 60 * 24) {
      text=LocaleController.formatString("WillUnmuteIn",R.string.WillUnmuteIn,LocaleController.formatPluralString("Hours",(int)Math.ceil(delta / 60.0f / 60)));
    }
 else     if (delta < 60 * 60 * 24 * 365) {
      text=LocaleController.formatString("WillUnmuteIn",R.string.WillUnmuteIn,LocaleController.formatPluralString("Days",(int)Math.ceil(delta / 60.0f / 60 / 24)));
    }
 else {
      text=null;
    }
  }
 else {
    if (value == 0) {
      enabled=true;
    }
 else     if (value == 1) {
      enabled=true;
    }
 else     if (value == 2) {
      enabled=false;
    }
 else {
      enabled=false;
    }
    if (enabled && custom) {
      text=LocaleController.getString("NotificationsCustom",R.string.NotificationsCustom);
    }
 else {
      text=enabled ? LocaleController.getString("NotificationsUnmuted",R.string.NotificationsUnmuted) : LocaleController.getString("NotificationsMuted",R.string.NotificationsMuted);
    }
  }
  if (text == null) {
    text=LocaleController.getString("NotificationsOff",R.string.NotificationsOff);
  }
  int lower_id=(int)exception.did;
  int high_id=(int)(exception.did >> 32);
  if (lower_id != 0) {
    if (lower_id > 0) {
      TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(lower_id);
      if (user != null) {
        setData(user,null,name,text,0,divider);
      }
    }
 else {
      TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-lower_id);
      if (chat != null) {
        setData(chat,null,name,text,0,divider);
      }
    }
  }
 else {
    TLRPC.EncryptedChat encryptedChat=MessagesController.getInstance(currentAccount).getEncryptedChat(high_id);
    if (encryptedChat != null) {
      TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(encryptedChat.user_id);
      if (user != null) {
        setData(user,encryptedChat,name,text,0,false);
      }
    }
  }
}
