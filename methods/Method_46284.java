protected void checkConnection() throws SofaRpcException {
  if (!isAvailable()) {
    throw new SofaRpcException(RpcErrorType.CLIENT_NETWORK,"channel is not available");
  }
}
