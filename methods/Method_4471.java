private void resolvePendingMessagePositions(){
  for (int i=pendingMessages.size() - 1; i >= 0; i--) {
    if (!resolvePendingMessagePosition(pendingMessages.get(i))) {
      pendingMessages.get(i).message.markAsProcessed(false);
      pendingMessages.remove(i);
    }
  }
  Collections.sort(pendingMessages);
}
