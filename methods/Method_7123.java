protected void processPendingEncMessages(){
  if (!pendingEncMessagesToDelete.isEmpty()) {
    final ArrayList<Long> pendingEncMessagesToDeleteCopy=new ArrayList<>(pendingEncMessagesToDelete);
    AndroidUtilities.runOnUIThread(() -> {
      for (int a=0; a < pendingEncMessagesToDeleteCopy.size(); a++) {
        MessageObject messageObject=MessagesController.getInstance(currentAccount).dialogMessagesByRandomIds.get(pendingEncMessagesToDeleteCopy.get(a));
        if (messageObject != null) {
          messageObject.deleted=true;
        }
      }
    }
);
    ArrayList<Long> arr=new ArrayList<>(pendingEncMessagesToDelete);
    MessagesStorage.getInstance(currentAccount).markMessagesAsDeletedByRandoms(arr);
    pendingEncMessagesToDelete.clear();
  }
}
