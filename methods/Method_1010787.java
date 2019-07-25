public void mark(SimpleEditorMessage message){
  for (  SimpleEditorMessage msg : getMessages()) {
    if (msg.sameAs(message))     return;
  }
synchronized (myMessagesLock) {
    addMessage(message);
    invalidateMessagesCaches();
  }
  if (message.showInGutter() && myEditor.hasUI()) {
    myEditor.getMessagesGutter().add(message);
  }
}
