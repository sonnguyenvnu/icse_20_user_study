@Override public void onAttachedToRecyclerView(final RecyclerView recyclerView){
  RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();
  if (layoutManager instanceof GridLayoutManager) {
    final GridLayoutManager gridLayoutManager=(GridLayoutManager)layoutManager;
    if (gridLayoutManager.getSpanSizeLookup() instanceof GridLayoutManager.DefaultSpanSizeLookup)     gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
      @Override public int getSpanSize(      int position){
        BaseWaveSideAdapter adapter=(BaseWaveSideAdapter)recyclerView.getAdapter();
        if (isFullSpanType(adapter.getItemViewType(position))) {
          return gridLayoutManager.getSpanCount();
        }
        return 1;
      }
    }
);
  }
}
