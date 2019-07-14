@Override public void obtainLithoViewChildren(List<LithoView> lithoViews){
  for (int i=0, size=mRecyclerView.getChildCount(); i < size; i++) {
    final View child=mRecyclerView.getChildAt(i);
    if (child instanceof LithoView) {
      lithoViews.add((LithoView)child);
    }
  }
}
