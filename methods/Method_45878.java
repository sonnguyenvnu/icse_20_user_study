@Override public ResponseFuture asyncSend(SofaRequest message,int timeout) throws SofaRpcException {
  throw new UnsupportedOperationException("Unsupported asynchronous RPC in short connection");
}
