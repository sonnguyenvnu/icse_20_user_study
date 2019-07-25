public void check(){
  for (  Valid valid : mValidQueue) {
    if (valid.preCheck()) {
      mValidQueue.remove(valid);
    }
  }
}
