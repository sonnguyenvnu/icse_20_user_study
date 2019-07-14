@Override public int frequency(long e){
  int count=super.frequency(e);
  if (doorkeeper.mightContain(e)) {
    count++;
  }
  return Math.min(count,15);
}
