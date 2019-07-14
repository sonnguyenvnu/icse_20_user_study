@Override protected V getNow() throws ExecutionException {
  if (cause != null) {
    throw new ExecutionException(cause);
  }
 else   if (result instanceof SofaResponse) {
    SofaResponse response=(SofaResponse)result;
    if (response.isError()) {
      cause=new SofaRpcException(RpcErrorType.SERVER_UNDECLARED_ERROR,response.getErrorMsg());
      throw new ExecutionException(cause);
    }
 else {
      result=response.getAppResponse();
      if (result instanceof Throwable) {
        throw new ExecutionException((Throwable)result);
      }
 else {
        return (V)result;
      }
    }
  }
 else {
    return (V)result;
  }
}
