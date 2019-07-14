private void addSpanLookup(ViewGroup parent){
  if (parent instanceof RecyclerView) {
    if (((RecyclerView)parent).getLayoutManager() instanceof GridLayoutManager) {
      GridLayoutManager layoutManager=((GridLayoutManager)((RecyclerView)parent).getLayoutManager());
      layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
        @Override public int getSpanSize(        int position){
          return getItemViewType(position) == PROGRESS_TYPE ? layoutManager.getSpanCount() : 1;
        }
      }
);
    }
  }
}
