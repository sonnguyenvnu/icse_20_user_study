@Override public SofaResponse invoke(FilterInvoker invoker,SofaRequest request) throws SofaRpcException {
  SofaHystrixInvokable command;
  if (RpcConstants.INVOKER_TYPE_SYNC.equals(request.getInvokeType())) {
    command=new SofaHystrixCommand(invoker,request);
  }
 else   if (RpcConstants.INVOKER_TYPE_FUTURE.equals(request.getInvokeType())) {
    command=new SofaAsyncHystrixCommand(invoker,request);
  }
 else {
    return invoker.invoke(request);
  }
  return command.invoke();
}
