@Override public void recordEviction(int weight){
  evictionCount.inc();
  evictionWeight.inc(weight);
}
