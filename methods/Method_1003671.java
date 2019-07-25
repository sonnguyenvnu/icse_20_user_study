public void reset(){
  if (handlerRegistered) {
    channelPipeline.remove(HANDLER_NAME);
  }
  if (!timeout.isZero()) {
    init(timeout);
  }
}
