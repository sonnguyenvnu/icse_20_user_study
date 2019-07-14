@Override public void shutDown(){
  super.clear();
  SampleVolleyFactory.getMemoryCache().clear();
}
