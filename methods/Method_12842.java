@Override public void appendLister(String name,AnalysisEventListener listener){
  if (!listeners.containsKey(name)) {
    listeners.put(name,listener);
  }
}
