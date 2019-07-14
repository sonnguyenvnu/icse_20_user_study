@Override public void writeAndFlush(final Object obj){
  Future future=channel.writeAndFlush(obj);
  future.addListener(new FutureListener(){
    @Override public void operationComplete(    Future future1) throws Exception {
      if (!future1.isSuccess()) {
        Throwable throwable=future1.cause();
        LOGGER.error("Failed to send to " + NetUtils.channelToString(localAddress(),remoteAddress()) + " for msg : " + obj + ", Cause by:",throwable);
      }
    }
  }
);
}
