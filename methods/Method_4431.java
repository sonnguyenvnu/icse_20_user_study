@Override @Deprecated @SuppressWarnings("deprecation") public void sendMessages(ExoPlayerMessage... messages){
  for (  ExoPlayerMessage message : messages) {
    createMessage(message.target).setType(message.messageType).setPayload(message.message).send();
  }
}
