@Override public void onHit(long key,QueueType queueType,boolean isFull){
  if (isFull) {
    hitsInSample++;
    if (queueType == QueueType.WINDOW) {
      hitsInWindow++;
    }
 else {
      hitsInMain++;
    }
  }
}
