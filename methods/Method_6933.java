private static int getUpdateQts(TLRPC.Update update){
  if (update instanceof TLRPC.TL_updateNewEncryptedMessage) {
    return ((TLRPC.TL_updateNewEncryptedMessage)update).qts;
  }
 else {
    return 0;
  }
}
