private void deliverMessage(PlayerMessage message) throws ExoPlaybackException {
  if (message.isCanceled()) {
    return;
  }
  try {
    message.getTarget().handleMessage(message.getType(),message.getPayload());
  }
  finally {
    message.markAsProcessed(true);
  }
}
