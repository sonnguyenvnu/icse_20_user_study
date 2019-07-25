public synchronized void commit(){
  if (activeSession <= 0) {
    throw new RevokingStoreIllegalStateException("activeSession has to be greater than 0");
  }
  --activeSession;
}
