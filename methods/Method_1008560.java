@Override public void close() throws IOException {
  List<Tuple<Translog.Location,Consumer<Boolean>>> oldListeners;
synchronized (this) {
    oldListeners=refreshListeners;
    refreshListeners=null;
    closed=true;
  }
  fireListeners(oldListeners);
}
