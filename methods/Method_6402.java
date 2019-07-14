private void saveDraftReplyMessage(final long did,final TLRPC.Message message){
  if (message == null) {
    return;
  }
  AndroidUtilities.runOnUIThread(() -> {
    TLRPC.DraftMessage draftMessage=drafts.get(did);
    if (draftMessage != null && draftMessage.reply_to_msg_id == message.id) {
      draftMessages.put(did,message);
      SerializedData serializedData=new SerializedData(message.getObjectSize());
      message.serializeToStream(serializedData);
      preferences.edit().putString("r_" + did,Utilities.bytesToHex(serializedData.toByteArray())).commit();
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.newDraftReceived,did);
      serializedData.cleanup();
    }
  }
);
}
