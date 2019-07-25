public void reset(boolean signal){
  lock.lock();
  try {
    result=null;
    hasResult=false;
    if (signal)     cond.signal(true);
  }
  finally {
    lock.unlock();
  }
}
