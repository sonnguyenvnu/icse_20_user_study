protected void recordClientElapseTime(){
  if (context != null) {
    Long startTime=(Long)context.removeAttachment(RpcConstants.INTERNAL_KEY_CLIENT_SEND_TIME);
    if (startTime != null) {
      context.setAttachment(RpcConstants.INTERNAL_KEY_CLIENT_ELAPSE,RpcRuntimeContext.now() - startTime);
    }
  }
}
