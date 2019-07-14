@Override public void shutDown(){
  super.clear();
  InstrumentedDraweeView.shutDown();
  SampleVolleyFactory.getMemoryCache().clear();
}
