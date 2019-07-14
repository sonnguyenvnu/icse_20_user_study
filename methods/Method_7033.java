public void updateDialogsWithDeletedMessages(final ArrayList<Integer> messages,final ArrayList<Long> additionalDialogsToUpdate,boolean useQueue,final int channelId){
  if (messages.isEmpty() && channelId == 0) {
    return;
  }
  if (useQueue) {
    storageQueue.postRunnable(() -> updateDialogsWithDeletedMessagesInternal(messages,additionalDialogsToUpdate,channelId));
  }
 else {
    updateDialogsWithDeletedMessagesInternal(messages,additionalDialogsToUpdate,channelId);
  }
}
