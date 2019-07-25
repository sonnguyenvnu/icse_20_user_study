private void panic(IllegalStateException e){
  if (isOpen()) {
    handleException(e);
    panicException=e;
    closeImmediately();
  }
  throw e;
}
