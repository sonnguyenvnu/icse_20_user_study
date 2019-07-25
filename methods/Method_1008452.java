@Override public void close(){
  transportService.removeConnectionListener(connectionListener);
}
