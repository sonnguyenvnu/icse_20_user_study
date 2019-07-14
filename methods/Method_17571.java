@Override public void onMiss(long key,boolean isFull){
  if (isFull) {
    missesInSample++;
  }
}
