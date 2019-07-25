public void increment(long credits,final long max_credits){
  lock.lock();
  try {
    credits_left=Math.min(max_credits,credits_left + credits);
    credits_available.signalAll();
  }
  finally {
    lock.unlock();
  }
}
