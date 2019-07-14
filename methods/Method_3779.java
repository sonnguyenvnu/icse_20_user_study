@Override public int scrollVerticallyBy(int dy,RecyclerView.Recycler recycler,RecyclerView.State state){
  updateMeasurements();
  ensureViewSet();
  return super.scrollVerticallyBy(dy,recycler,state);
}
