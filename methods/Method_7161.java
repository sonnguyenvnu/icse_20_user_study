public boolean isSendingCallback(MessageObject messageObject,TLRPC.KeyboardButton button){
  if (messageObject == null || button == null) {
    return false;
  }
  int type;
  if (button instanceof TLRPC.TL_keyboardButtonUrlAuth) {
    type=3;
  }
 else   if (button instanceof TLRPC.TL_keyboardButtonGame) {
    type=1;
  }
 else   if (button instanceof TLRPC.TL_keyboardButtonBuy) {
    type=2;
  }
 else {
    type=0;
  }
  final String key=messageObject.getDialogId() + "_" + messageObject.getId() + "_" + Utilities.bytesToHex(button.data) + "_" + type;
  return waitingForCallback.containsKey(key);
}
