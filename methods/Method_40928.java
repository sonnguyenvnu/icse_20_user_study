@Override public synchronized void recordSuccess(){
  bitSet.setNext(true);
  checkThreshold();
}
