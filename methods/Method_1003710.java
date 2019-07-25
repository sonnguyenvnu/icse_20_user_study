public static <T>WebSocketConnector<T> websocket(Context context,Function<WebSocket,T> openAction){
  return new DefaultWebSocketConnector<>(context,openAction);
}
