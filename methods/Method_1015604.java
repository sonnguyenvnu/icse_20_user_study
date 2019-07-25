@Override public void stop(){
  deliverThread.interrupt();
  deliverThread=null;
}
