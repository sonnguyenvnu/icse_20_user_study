@Override public Result invoke(Invoker<?> invoker,Invocation invocation) throws RpcException {
  if ("$echo".equals(invocation.getMethodName())) {
    return invoker.invoke(invocation);
  }
  RpcContext rpcContext=RpcContext.getContext();
  if (StringUtils.isBlank(this.appName)) {
    this.appName=SofaTracerConfiguration.getProperty(SofaTracerConfiguration.TRACER_APPNAME_KEY);
  }
  String spanKind=spanKind(rpcContext);
  Result result;
  if (spanKind.equals(Tags.SPAN_KIND_SERVER)) {
    result=doServerFilter(rpcContext,invoker,invocation);
  }
 else {
    result=doClientFilter(rpcContext,invoker,invocation);
  }
  return result;
}
