@Override public int scrollHorizontallyBy(int dx,RecyclerView.Recycler recycler,RecyclerView.State state){
  updateMeasurements();
  ensureViewSet();
  return super.scrollHorizontallyBy(dx,recycler,state);
}
