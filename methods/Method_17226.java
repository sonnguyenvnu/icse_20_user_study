@Override public void recordEviction(int weight,RemovalCause cause){
  evictionCount.increment();
  evictionWeight.add(weight);
}
