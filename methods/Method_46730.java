@Override public MessageDto loadResult() throws RpcException {
  MessageDto msg=rpcContent.getRes();
  if (msg == null) {
    throw new RpcException("request timeout.");
  }
  log.debug("got response. {} ",getKey());
  return msg;
}
