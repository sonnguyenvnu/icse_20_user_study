@Override public void halt(){
  running.set(false);
  sequenceBarrier.alert();
}
