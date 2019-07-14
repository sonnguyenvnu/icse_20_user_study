public String getMusicAuthor(boolean unknown){
  TLRPC.Document document=getDocument();
  if (document != null) {
    boolean isVoice=false;
    for (int a=0; a < document.attributes.size(); a++) {
      TLRPC.DocumentAttribute attribute=document.attributes.get(a);
      if (attribute instanceof TLRPC.TL_documentAttributeAudio) {
        if (attribute.voice) {
          isVoice=true;
        }
 else {
          String performer=attribute.performer;
          if (TextUtils.isEmpty(performer) && unknown) {
            performer=LocaleController.getString("AudioUnknownArtist",R.string.AudioUnknownArtist);
          }
          return performer;
        }
      }
 else       if (attribute instanceof TLRPC.TL_documentAttributeVideo) {
        if (attribute.round_message) {
          isVoice=true;
        }
      }
      if (isVoice) {
        if (!unknown) {
          return null;
        }
        if (isOutOwner() || messageOwner.fwd_from != null && messageOwner.fwd_from.from_id == UserConfig.getInstance(currentAccount).getClientUserId()) {
          return LocaleController.getString("FromYou",R.string.FromYou);
        }
        TLRPC.User user=null;
        TLRPC.Chat chat=null;
        if (messageOwner.fwd_from != null && messageOwner.fwd_from.channel_id != 0) {
          chat=MessagesController.getInstance(currentAccount).getChat(messageOwner.fwd_from.channel_id);
        }
 else         if (messageOwner.fwd_from != null && messageOwner.fwd_from.from_id != 0) {
          user=MessagesController.getInstance(currentAccount).getUser(messageOwner.fwd_from.from_id);
        }
 else         if (messageOwner.fwd_from != null && messageOwner.fwd_from.from_name != null) {
          return messageOwner.fwd_from.from_name;
        }
 else         if (messageOwner.from_id < 0) {
          chat=MessagesController.getInstance(currentAccount).getChat(-messageOwner.from_id);
        }
 else         if (messageOwner.from_id == 0 && messageOwner.to_id.channel_id != 0) {
          chat=MessagesController.getInstance(currentAccount).getChat(messageOwner.to_id.channel_id);
        }
 else {
          user=MessagesController.getInstance(currentAccount).getUser(messageOwner.from_id);
        }
        if (user != null) {
          return UserObject.getUserName(user);
        }
 else         if (chat != null) {
          return chat.title;
        }
      }
    }
  }
  return LocaleController.getString("AudioUnknownArtist",R.string.AudioUnknownArtist);
}
