@Override public <T>T lock(Callable<T> callable){
  connection.restoreOriginalState();
  return connection.lock(table,callable);
}
