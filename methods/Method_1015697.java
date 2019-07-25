@Override public boolean decrement(Message msg,int credits,long timeout){
  lock.lock();
  try {
    if (queuing)     return addToQueue(msg,credits);
    if (decrement(credits))     return true;
    queuing=true;
    return addToQueue(msg,credits);
  }
  finally {
    lock.unlock();
  }
}
