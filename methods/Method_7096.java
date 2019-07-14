private int addToPopupMessages(final ArrayList<MessageObject> popupArrayAdd,MessageObject messageObject,int lower_id,long dialog_id,boolean isChannel,SharedPreferences preferences){
  int popup=0;
  if (lower_id != 0) {
    if (preferences.getBoolean("custom_" + dialog_id,false)) {
      popup=preferences.getInt("popup_" + dialog_id,0);
    }
 else {
      popup=0;
    }
    if (popup == 0) {
      if (isChannel) {
        popup=preferences.getInt("popupChannel",0);
      }
 else {
        popup=preferences.getInt((int)dialog_id < 0 ? "popupGroup" : "popupAll",0);
      }
    }
 else     if (popup == 1) {
      popup=3;
    }
 else     if (popup == 2) {
      popup=0;
    }
  }
  if (popup != 0 && messageObject.messageOwner.to_id.channel_id != 0 && !messageObject.isMegagroup()) {
    popup=0;
  }
  if (popup != 0) {
    popupArrayAdd.add(0,messageObject);
  }
  return popup;
}
