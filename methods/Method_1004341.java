@Override public void connect(){
  WebSocketListener webSocketListener=new WebSocketListener(this);
  if (!this.webSocketListener.compareAndSet(null,webSocketListener)) {
    throw new IllegalStateException("Already connected");
  }
  webSocket.set(webSocketConnectionFactory.newWebSocket(webSocketRequest,webSocketListener));
}
