public void pause(){
  myPaused.compareAndSet(false,true);
}
