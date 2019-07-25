public void request(int numMessages){
  if (subscription == null) {
    deferredInitialMessageRequest+=numMessages;
    return;
  }
  deframer.request(numMessages);
  requestHttpFrame();
}
