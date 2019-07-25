@Override public synchronized boolean unregister(HistogramInterface histogram){
  String key=histogram.getName();
  return histograms.remove(key) != null;
}
