public int size(){
  lock.lock();
  try {
    long num=msgs.values().stream().flatMap(Collection::stream).map(Message::size).reduce(0L,(a,b) -> a + b);
    return (int)num;
  }
  finally {
    lock.unlock();
  }
}
