void completed(){
  done=true;
  ackSendQueue.ackCompleted(this);
}
