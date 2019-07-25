@Override public void close(){
  poolLock.lock();
  try {
    for (    S availableConnection : availableConnections) {
      connectionFactory.destroy(availableConnection);
    }
  }
  finally {
    closed=true;
    poolLock.unlock();
  }
}
