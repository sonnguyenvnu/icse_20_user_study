public synchronized boolean put(T t){
  if (curPointer == -1 || curPointer < objsPool.length - 1) {
    curPointer++;
    objsPool[curPointer]=t;
    return true;
  }
  return false;
}
