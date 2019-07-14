private void updateAdapter(){
  if (mSimpleAdapter == null) {
    return;
  }
  if (mConfig.infiniteDataSource) {
    mSimpleAdapter=SimpleAdapter.Util.makeItInfinite(mSimpleAdapter);
    if (mDistinctUriCompatible && mConfig.distinctUriDataSource) {
      mSimpleAdapter=SimpleAdapter.Util.decorate(mSimpleAdapter,DistinctUriDecorator.SINGLETON);
    }
  }
switch (mConfig.recyclerLayoutType) {
case Const.RECYCLER_VIEW_LAYOUT_VALUE:
case Const.GRID_RECYCLER_VIEW_LAYOUT_VALUE:
    mDraweeViewAdapter=new DraweeViewAdapter(getContext(),mSimpleAdapter,mConfig,mPerfListener);
  mRecyclerView.setAdapter(mDraweeViewAdapter);
break;
case Const.LISTVIEW_LAYOUT_VALUE:
mListAdapter=new DraweeViewListAdapter(getContext(),mSimpleAdapter,mConfig,mPerfListener);
mListView.setAdapter(mListAdapter);
break;
default :
throw new IllegalStateException("Recycler Layout not supported");
}
}
