@Override public void close(){
  if (_connections != null) {
    _connections.clear();
  }
}
