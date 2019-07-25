public void finish(){
  clients.remove(this);
  if (isDone)   return;
  isDone=true;
  decoder.listener.onCompleted();
}
