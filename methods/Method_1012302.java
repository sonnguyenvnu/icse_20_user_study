public void detach(int count){
  int childCount=mViews.size();
  while (childCount > 0 && count > 0) {
    V view=mViews.remove(childCount - 1);
    if (mCachePool == null) {
      mCachePool=new Pools.SimplePool<>(12);
    }
    Object notCacheTag=view.getTag(R.id.xui_view_can_not_cache_tag);
    if (notCacheTag == null || !(boolean)notCacheTag) {
      try {
        mCachePool.release(view);
      }
 catch (      Exception ignored) {
      }
    }
    mParentView.removeView(view);
    childCount--;
    count--;
  }
}
