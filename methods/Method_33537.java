public void scrollRecycleView(){
  bindingView.xrvBook.addOnScrollListener(new RecyclerView.OnScrollListener(){
    @Override public void onScrollStateChanged(    RecyclerView recyclerView,    int newState){
      super.onScrollStateChanged(recyclerView,newState);
      if (newState == RecyclerView.SCROLL_STATE_IDLE) {
        lastVisibleItem=mLayoutManager.findLastVisibleItemPosition();
        if (mBookAdapter == null) {
          return;
        }
        if (mLayoutManager.getItemCount() == 0) {
          mBookAdapter.updateLoadStatus(BookAdapter.LOAD_NONE);
          return;
        }
        if (lastVisibleItem + 1 == mLayoutManager.getItemCount() && mBookAdapter.getLoadStatus() != BookAdapter.LOAD_MORE) {
          mBookAdapter.updateLoadStatus(BookAdapter.LOAD_MORE);
          new Handler().postDelayed(new Runnable(){
            @Override public void run(){
              mStart+=mCount;
              loadCustomData();
            }
          }
,1000);
        }
      }
    }
    @Override public void onScrolled(    RecyclerView recyclerView,    int dx,    int dy){
      super.onScrolled(recyclerView,dx,dy);
      lastVisibleItem=mLayoutManager.findLastVisibleItemPosition();
    }
  }
);
}
