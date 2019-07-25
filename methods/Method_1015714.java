protected void resize(int new_size){
  lock.lock();
  try {
    ping_rsps=Arrays.copyOf(ping_rsps,new_size);
  }
  finally {
    lock.unlock();
  }
}
