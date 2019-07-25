public void unmark(SimpleEditorMessage message){
synchronized (myMessagesLock) {
    if (removeMessage(message)) {
      invalidateMessagesCaches();
    }
  }
}
