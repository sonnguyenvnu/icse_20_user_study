@Override public void alert(){
  alerted=true;
  waitStrategy.signalAllWhenBlocking();
}
