@Override public void increment(long e){
  counts.put(e,counts.get(e) + 1);
  size++;
  if (size == sampleSize) {
    reset();
  }
}
