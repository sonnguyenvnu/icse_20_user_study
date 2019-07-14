@Override public void onItemsChanged(RecyclerView recyclerView){
  mSpanSizeLookup.invalidateSpanIndexCache();
  mSpanSizeLookup.invalidateSpanGroupIndexCache();
}
