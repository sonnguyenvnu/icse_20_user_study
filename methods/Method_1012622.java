private void debug(String msg){
  if (instance == null || factories.isEmpty()) {
    return;
  }
  P6LogQuery.debug(msg);
}
