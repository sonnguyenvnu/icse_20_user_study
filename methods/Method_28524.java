public void attachRecyclerView(final RecyclerView recyclerView){
  if (this.recyclerView == null) {
    this.recyclerView=recyclerView;
    this.layoutManager=recyclerView.getLayoutManager();
    this.recyclerView.addOnScrollListener(onScrollListener);
    if (recyclerView.getAdapter() != null && !registeredObserver) {
      recyclerView.getAdapter().registerAdapterDataObserver(observer);
      registeredObserver=true;
    }
    hideShow();
    initScrollHeight();
  }
}
