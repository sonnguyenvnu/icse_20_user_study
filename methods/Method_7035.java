public ArrayList<Long> markMessagesAsDeleted(final int channelId,final int mid,boolean useQueue){
  if (useQueue) {
    storageQueue.postRunnable(() -> markMessagesAsDeletedInternal(channelId,mid));
  }
 else {
    return markMessagesAsDeletedInternal(channelId,mid);
  }
  return null;
}
