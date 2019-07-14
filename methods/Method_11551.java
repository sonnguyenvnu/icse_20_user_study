private int incrForLoop(){
  int p=pointer.incrementAndGet();
  int size=proxies.size();
  if (p < size) {
    return p;
  }
  while (!pointer.compareAndSet(p,p % size)) {
    p=pointer.get();
  }
  return p % size;
}
