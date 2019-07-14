@Override public void finished(){
  if (debug) {
    System.out.println("recency gain = " + gain);
  }
  checkState(data.size() <= maximumSize,data.size());
}
