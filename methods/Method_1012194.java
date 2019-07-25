public BaseRecyclerAdapter<T> refresh(Collection<T> collection){
  if (collection != null) {
    mData.clear();
    mData.addAll(collection);
    notifyDataSetChanged();
    mLastPosition=-1;
  }
  return this;
}
