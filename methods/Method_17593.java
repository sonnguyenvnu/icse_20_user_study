@Override public void onHit(long key,QueueType queue,boolean isFull){
  if (isFull) {
    indicator.record(key);
  }
}
