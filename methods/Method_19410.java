@Override public void init(SectionsRecyclerView SectionsRecyclerView){
  if (SectionsRecyclerView == null) {
    throw new RuntimeException(RECYCLER_ARGUMENT_NULL);
  }
  if (mSectionsRecyclerView != null) {
    throw new RuntimeException(RECYCLER_ALREADY_INITIALIZED);
  }
  mSectionsRecyclerView=SectionsRecyclerView;
  mSectionsRecyclerView.hideStickyHeader();
  mLayoutManager=SectionsRecyclerView.getRecyclerView().getLayoutManager();
  if (mLayoutManager == null) {
    throw new RuntimeException(LAYOUTMANAGER_NOT_INITIALIZED);
  }
  mSectionsRecyclerView.getRecyclerView().addOnScrollListener(this);
}
