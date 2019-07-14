/** 
 * ?????filter
 * @param requestContext ContainerRequestContext
 */
public static void serverFilter(ContainerRequestContext requestContext){
  try {
    SofaTraceContext sofaTraceContext=SofaTraceContextHolder.getSofaTraceContext();
    SofaTracerSpan serverSpan=sofaTraceContext.getCurrentSpan();
    if (serverSpan != null) {
      RpcInternalContext context=RpcInternalContext.getContext();
      context.setAttachment(RpcConstants.INTERNAL_KEY_SERVER_RECEIVE_TIME,RpcRuntimeContext.now());
      SofaResourceMethodInvoker resourceMethodInvoker=(SofaResourceMethodInvoker)((PostMatchContainerRequestContext)requestContext).getResourceMethod();
      SofaResourceFactory factory=resourceMethodInvoker.getResource();
      String serviceName=factory.getServiceName();
      String appName=factory.getAppName();
      if (serviceName == null) {
        serviceName=resourceMethodInvoker.getResourceClass().getName();
      }
      serverSpan.setTag(RpcSpanTags.SERVICE,serviceName);
      if (resourceMethodInvoker.getMethod() != null) {
        serverSpan.setTag(RpcSpanTags.METHOD,resourceMethodInvoker.getMethod().getName());
        context.setAttachment(METHOD_TYPE_STRING,resourceMethodInvoker.getMethod());
      }
      serverSpan.setTag(RpcSpanTags.REMOTE_IP,context.getRemoteHostName());
      String remoteAppName=requestContext.getHeaderString(RemotingConstants.HEAD_APP_NAME);
      if (StringUtils.isNotBlank(remoteAppName)) {
        serverSpan.setTag(RpcSpanTags.REMOTE_APP,remoteAppName);
      }
      serverSpan.setTag(RpcSpanTags.PROTOCOL,RpcConstants.PROTOCOL_TYPE_REST);
      serverSpan.setTag(RpcSpanTags.INVOKE_TYPE,RpcConstants.INVOKER_TYPE_SYNC);
      if (appName == null) {
        appName=(String)RpcRuntimeContext.get(RpcRuntimeContext.KEY_APPNAME);
      }
      serverSpan.setTag(RpcSpanTags.LOCAL_APP,appName);
    }
  }
 catch (  Throwable t) {
    if (LOGGER.isWarnEnabled()) {
      LOGGER.warn("the process of rest tracer server filter occur error ",t);
    }
  }
}
