@Override public void shutdown(boolean reset){
  if (reset) {
    NetworkUtil.quietlySetLinger(socket);
  }
  NetworkUtil.quietlyClose(socket);
  NetworkUtil.quietlyClose(reader);
  NetworkUtil.quietlyClose(writer);
  socket=null;
  writer=null;
  reader=null;
  if (!initiatedFromClient) {
    fireConnectionStopped();
  }
}
