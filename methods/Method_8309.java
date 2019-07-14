private String getMessageContent(MessageObject messageObject,int previousUid,boolean name){
  String str="";
  if (name) {
    if (previousUid != messageObject.messageOwner.from_id) {
      if (messageObject.messageOwner.from_id > 0) {
        TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(messageObject.messageOwner.from_id);
        if (user != null) {
          str=ContactsController.formatName(user.first_name,user.last_name) + ":\n";
        }
      }
 else       if (messageObject.messageOwner.from_id < 0) {
        TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-messageObject.messageOwner.from_id);
        if (chat != null) {
          str=chat.title + ":\n";
        }
      }
    }
  }
  if (messageObject.type == 0 && messageObject.messageOwner.message != null) {
    str+=messageObject.messageOwner.message;
  }
 else   if (messageObject.messageOwner.media != null && messageObject.messageOwner.message != null) {
    str+=messageObject.messageOwner.message;
  }
 else {
    str+=messageObject.messageText;
  }
  return str;
}
