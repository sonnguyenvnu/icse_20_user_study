protected void append(ResourceListType more){
  super.set(addAll(get(),more));
  mCanLoadMore=getSize(more) == mLoadCount;
}
