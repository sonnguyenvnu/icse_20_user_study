public void sendCurrentLocation(final MessageObject messageObject,final TLRPC.KeyboardButton button){
  if (messageObject == null || button == null) {
    return;
  }
  final String key=messageObject.getDialogId() + "_" + messageObject.getId() + "_" + Utilities.bytesToHex(button.data) + "_" + (button instanceof TLRPC.TL_keyboardButtonGame ? "1" : "0");
  waitingForLocation.put(key,messageObject);
  locationProvider.start();
}
