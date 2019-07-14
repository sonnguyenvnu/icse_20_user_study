public void generateCaption(){
  if (caption != null || isRoundVideo()) {
    return;
  }
  if (!isMediaEmpty() && !(messageOwner.media instanceof TLRPC.TL_messageMediaGame) && !TextUtils.isEmpty(messageOwner.message)) {
    caption=Emoji.replaceEmoji(messageOwner.message,Theme.chat_msgTextPaint.getFontMetricsInt(),AndroidUtilities.dp(20),false);
    boolean hasEntities;
    if (messageOwner.send_state != MESSAGE_SEND_STATE_SENT) {
      hasEntities=false;
      for (int a=0; a < messageOwner.entities.size(); a++) {
        if (!(messageOwner.entities.get(a) instanceof TLRPC.TL_inputMessageEntityMentionName)) {
          hasEntities=true;
          break;
        }
      }
    }
 else {
      hasEntities=!messageOwner.entities.isEmpty();
    }
    boolean useManualParse=!hasEntities && (eventId != 0 || messageOwner.media instanceof TLRPC.TL_messageMediaPhoto_old || messageOwner.media instanceof TLRPC.TL_messageMediaPhoto_layer68 || messageOwner.media instanceof TLRPC.TL_messageMediaPhoto_layer74 || messageOwner.media instanceof TLRPC.TL_messageMediaDocument_old || messageOwner.media instanceof TLRPC.TL_messageMediaDocument_layer68 || messageOwner.media instanceof TLRPC.TL_messageMediaDocument_layer74 || isOut() && messageOwner.send_state != MESSAGE_SEND_STATE_SENT || messageOwner.id < 0);
    if (useManualParse) {
      if (containsUrls(caption)) {
        try {
          Linkify.addLinks((Spannable)caption,Linkify.WEB_URLS | Linkify.PHONE_NUMBERS);
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
      addUsernamesAndHashtags(isOutOwner(),caption,true,0);
    }
 else {
      try {
        Linkify.addLinks((Spannable)caption,Linkify.PHONE_NUMBERS);
      }
 catch (      Throwable e) {
        FileLog.e(e);
      }
    }
    addEntitiesToText(caption,useManualParse);
  }
}
