public String dump(){
  lock.lock();
  try {
    return this.toString();
  }
  finally {
    lock.unlock();
  }
}
