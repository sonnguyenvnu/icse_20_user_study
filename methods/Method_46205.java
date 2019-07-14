@Override public <Response extends ResponseCommand>boolean serializeHeader(Response response) throws SerializationException {
  if (response instanceof RpcResponseCommand) {
    RpcInternalContext.getContext().getStopWatch().tick();
    Object responseObject=((RpcResponseCommand)response).getResponseObject();
    if (responseObject instanceof SofaResponse) {
      SofaResponse sofaResponse=(SofaResponse)responseObject;
      if (sofaResponse.isError() || sofaResponse.getAppResponse() instanceof Throwable) {
        sofaResponse.addResponseProp(RemotingConstants.HEAD_RESPONSE_ERROR,StringUtils.TRUE);
      }
      response.setHeader(mapSerializer.encode(sofaResponse.getResponseProps()));
    }
    return true;
  }
  return false;
}
