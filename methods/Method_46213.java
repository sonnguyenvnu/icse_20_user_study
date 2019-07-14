private Long getClientTimeoutFromGenericContext(String method,Object[] args) throws SofaRpcException {
  if (METHOD_GENERIC_INVOKE.equals(method)) {
    if (args.length == 4 && args[3] instanceof GenericContext) {
      return ((GenericContext)args[3]).getClientTimeout();
    }
 else     if (args.length == 5 && args[4] instanceof GenericContext) {
      return ((GenericContext)args[4]).getClientTimeout();
    }
  }
  return null;
}
