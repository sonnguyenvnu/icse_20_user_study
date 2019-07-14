@Override @SuppressWarnings("deprecation") public void recordEviction(int weight){
  evictionCount.increment();
  evictionWeight.add(weight);
}
