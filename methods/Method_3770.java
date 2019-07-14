@Override public void onItemsMoved(RecyclerView recyclerView,int from,int to,int itemCount){
  mSpanSizeLookup.invalidateSpanIndexCache();
  mSpanSizeLookup.invalidateSpanGroupIndexCache();
}
