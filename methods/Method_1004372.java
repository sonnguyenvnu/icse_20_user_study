public void ack(){
  if (!completing.compareAndSet(false,true)) {
    return;
  }
  completed();
}
