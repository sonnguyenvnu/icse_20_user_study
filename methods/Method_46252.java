protected void checkConnection() throws SofaRpcException {
  Connection connection=fetchConnection();
  if (connection == null) {
    throw new SofaRpcException(RpcErrorType.CLIENT_NETWORK,"connection is null");
  }
  if (!connection.isFine()) {
    throw new SofaRpcException(RpcErrorType.CLIENT_NETWORK,"connection is not fine");
  }
}
