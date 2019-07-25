public int size(){
  lock.lock();
  try {
    return responses.size();
  }
  finally {
    lock.unlock();
  }
}
