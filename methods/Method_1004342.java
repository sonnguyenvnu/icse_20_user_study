@Override public void disconnect(OperationClientMessage message){
  WebSocket socket=webSocket.getAndSet(null);
  if (socket != null) {
    socket.close(1001,message.toJsonString());
  }
  release();
}
