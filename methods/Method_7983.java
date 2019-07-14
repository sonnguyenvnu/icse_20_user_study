private void measureTime(MessageObject messageObject){
  CharSequence signString;
  if (messageObject.messageOwner.post_author != null) {
    signString=messageObject.messageOwner.post_author.replace("\n","");
  }
 else   if (messageObject.messageOwner.fwd_from != null && messageObject.messageOwner.fwd_from.post_author != null) {
    signString=messageObject.messageOwner.fwd_from.post_author.replace("\n","");
  }
 else   if (!messageObject.isOutOwner() && messageObject.messageOwner.from_id > 0 && messageObject.messageOwner.post) {
    TLRPC.User signUser=MessagesController.getInstance(currentAccount).getUser(messageObject.messageOwner.from_id);
    if (signUser != null) {
      signString=ContactsController.formatName(signUser.first_name,signUser.last_name).replace('\n',' ');
    }
 else {
      signString=null;
    }
  }
 else {
    signString=null;
  }
  String timeString;
  TLRPC.User author=null;
  if (currentMessageObject.isFromUser()) {
    author=MessagesController.getInstance(currentAccount).getUser(messageObject.messageOwner.from_id);
  }
  boolean edited;
  if (messageObject.isLiveLocation() || messageObject.getDialogId() == 777000 || messageObject.messageOwner.via_bot_id != 0 || messageObject.messageOwner.via_bot_name != null || author != null && author.bot) {
    edited=false;
  }
 else   if (currentPosition == null || currentMessagesGroup == null) {
    edited=(messageObject.messageOwner.flags & TLRPC.MESSAGE_FLAG_EDITED) != 0 || messageObject.isEditing();
  }
 else {
    edited=false;
    for (int a=0, size=currentMessagesGroup.messages.size(); a < size; a++) {
      MessageObject object=currentMessagesGroup.messages.get(a);
      if ((object.messageOwner.flags & TLRPC.MESSAGE_FLAG_EDITED) != 0 || object.isEditing()) {
        edited=true;
        break;
      }
    }
  }
  if (edited) {
    timeString=LocaleController.getString("EditedMessage",R.string.EditedMessage) + " " + LocaleController.getInstance().formatterDay.format((long)(messageObject.messageOwner.date) * 1000);
  }
 else {
    timeString=LocaleController.getInstance().formatterDay.format((long)(messageObject.messageOwner.date) * 1000);
  }
  if (signString != null) {
    currentTimeString=", " + timeString;
  }
 else {
    currentTimeString=timeString;
  }
  timeTextWidth=timeWidth=(int)Math.ceil(Theme.chat_timePaint.measureText(currentTimeString));
  if ((messageObject.messageOwner.flags & TLRPC.MESSAGE_FLAG_HAS_VIEWS) != 0) {
    currentViewsString=String.format("%s",LocaleController.formatShortNumber(Math.max(1,messageObject.messageOwner.views),null));
    viewsTextWidth=(int)Math.ceil(Theme.chat_timePaint.measureText(currentViewsString));
    timeWidth+=viewsTextWidth + Theme.chat_msgInViewsDrawable.getIntrinsicWidth() + AndroidUtilities.dp(10);
  }
  if (signString != null) {
    if (availableTimeWidth == 0) {
      availableTimeWidth=AndroidUtilities.dp(1000);
    }
    int widthForSign=availableTimeWidth - timeWidth;
    if (messageObject.isOutOwner()) {
      if (messageObject.type == MessageObject.TYPE_ROUND_VIDEO) {
        widthForSign-=AndroidUtilities.dp(20);
      }
 else {
        widthForSign-=AndroidUtilities.dp(96);
      }
    }
    int width=(int)Math.ceil(Theme.chat_timePaint.measureText(signString,0,signString.length()));
    if (width > widthForSign) {
      if (widthForSign <= 0) {
        signString="";
        width=0;
      }
 else {
        signString=TextUtils.ellipsize(signString,Theme.chat_timePaint,widthForSign,TextUtils.TruncateAt.END);
        width=widthForSign;
      }
    }
    currentTimeString=signString + currentTimeString;
    timeTextWidth+=width;
    timeWidth+=width;
  }
}
