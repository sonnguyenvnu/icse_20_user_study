private void sendMessageToTargetThread(final PlayerMessage message){
  Handler handler=message.getHandler();
  handler.post(() -> {
    try {
      deliverMessage(message);
    }
 catch (    ExoPlaybackException e) {
      Log.e(TAG,"Unexpected error delivering message on external thread.",e);
      throw new RuntimeException(e);
    }
  }
);
}
