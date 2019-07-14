public boolean isSendingCurrentLocation(MessageObject messageObject,TLRPC.KeyboardButton button){
  if (messageObject == null || button == null) {
    return false;
  }
  final String key=messageObject.getDialogId() + "_" + messageObject.getId() + "_" + Utilities.bytesToHex(button.data) + "_" + (button instanceof TLRPC.TL_keyboardButtonGame ? "1" : "0");
  return waitingForLocation.containsKey(key);
}
