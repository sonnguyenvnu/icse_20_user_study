public static void sendRestServerSendEvent(RestServerSendEvent restServerSendEvent){
  if (EventBus.isEnable(ServerSendEvent.class)) {
    SofaRequest request=new SofaRequest();
    String appName=(String)RpcRuntimeContext.get(RpcRuntimeContext.KEY_APPNAME);
    request.setTargetAppName(appName);
    request.addRequestProp(RemotingConstants.HEAD_APP_NAME,restServerSendEvent.getRequest().getHttpHeaders().getHeaderString(RemotingConstants.HEAD_APP_NAME));
    RpcInternalContext context=RpcInternalContext.getContext();
    request.setTargetServiceUniqueName((String)context.getAttachment(INTERNAL_KEY_PREFIX + RestConstants.REST_SERVICE_KEY));
    request.setMethodName((String)context.getAttachment(INTERNAL_KEY_PREFIX + RestConstants.REST_METHODNAME_KEY));
    request.addRequestProp(RemotingConstants.HEAD_PROTOCOL,RpcConstants.PROTOCOL_TYPE_REST);
    request.setInvokeType(RpcConstants.INVOKER_TYPE_SYNC);
    SofaResponse response=new SofaResponse();
    if (restServerSendEvent.getThrowable() != null) {
      response.setErrorMsg(restServerSendEvent.getThrowable().getMessage());
    }
    final ServerSendEvent event=new ServerSendEvent(request,response,restServerSendEvent.getThrowable());
    EventBus.post(event);
  }
}
