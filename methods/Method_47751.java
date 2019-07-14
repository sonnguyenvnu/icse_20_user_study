private void logSocketEvent(Socket socket,String event,final String msg){
  socket.on(event,args -> {
    Log.i("SyncManager",msg);
    for (    Object o : args)     if (o instanceof SocketIOException)     ((SocketIOException)o).printStackTrace();
  }
);
}
