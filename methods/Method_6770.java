public static long getDialogId(TLRPC.Message message){
  if (message.dialog_id == 0 && message.to_id != null) {
    if (message.to_id.chat_id != 0) {
      if (message.to_id.chat_id < 0) {
        message.dialog_id=AndroidUtilities.makeBroadcastId(message.to_id.chat_id);
      }
 else {
        message.dialog_id=-message.to_id.chat_id;
      }
    }
 else     if (message.to_id.channel_id != 0) {
      message.dialog_id=-message.to_id.channel_id;
    }
 else     if (isOut(message)) {
      message.dialog_id=message.to_id.user_id;
    }
 else {
      message.dialog_id=message.from_id;
    }
  }
  return message.dialog_id;
}
