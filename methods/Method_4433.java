@Override @Deprecated @SuppressWarnings("deprecation") public void blockingSendMessages(ExoPlayerMessage... messages){
  List<PlayerMessage> playerMessages=new ArrayList<>();
  for (  ExoPlayerMessage message : messages) {
    playerMessages.add(createMessage(message.target).setType(message.messageType).setPayload(message.message).send());
  }
  boolean wasInterrupted=false;
  for (  PlayerMessage message : playerMessages) {
    boolean blockMessage=true;
    while (blockMessage) {
      try {
        message.blockUntilDelivered();
        blockMessage=false;
      }
 catch (      InterruptedException e) {
        wasInterrupted=true;
      }
    }
  }
  if (wasInterrupted) {
    Thread.currentThread().interrupt();
  }
}
