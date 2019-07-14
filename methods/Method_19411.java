@Override public void reset(){
  if (mSectionsRecyclerView == null) {
    throw new IllegalStateException(RECYCLER_NOT_INITIALIZED);
  }
  mSectionsRecyclerView.getRecyclerView().removeOnScrollListener(this);
  mLayoutManager=null;
  mSectionsRecyclerView=null;
}
