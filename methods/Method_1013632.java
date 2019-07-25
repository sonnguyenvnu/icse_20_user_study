@Override public RECEIVER_RESULT receive(Channel channel,ByteBuf byteBuf){
  if (future().isDone()) {
    logger.debug("[receive][done, return]{}",channel);
    return RECEIVER_RESULT.ALREADY_FINISH;
  }
  try {
    V result=doReceiveResponse(channel,byteBuf);
    if (result != null) {
      if (logResponse()) {
        logger.info("[receive]{}, {}",ChannelUtil.getDesc(channel),result);
      }
      if (!future().isDone()) {
        future().setSuccess(result);
      }
    }
    if (result == null) {
      return RECEIVER_RESULT.CONTINUE;
    }
    return RECEIVER_RESULT.SUCCESS;
  }
 catch (  Exception e) {
    future().setFailure(e);
    if (e instanceof ProtocalErrorResponse) {
      return RECEIVER_RESULT.SUCCESS;
    }
  }
  return RECEIVER_RESULT.FAIL;
}
