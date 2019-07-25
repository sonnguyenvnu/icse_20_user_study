@Override public void run(){
  try {
    check();
  }
 catch (  Throwable e) {
    LOG.error("consumer status checker task failed.",e);
  }
}
