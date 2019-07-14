@Override public synchronized void recordFailure(){
  bitSet.setNext(false);
  checkThreshold();
}
