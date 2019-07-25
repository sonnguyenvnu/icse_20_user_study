public void flush(){
  while (!mQueue.isEmpty()) {
    mQueue.remove();
  }
}
