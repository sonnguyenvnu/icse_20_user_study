public void finish(Throwable t){
  clients.remove(this);
  if (isDone)   return;
  isDone=true;
  decoder.listener.onThrowable(t);
}
