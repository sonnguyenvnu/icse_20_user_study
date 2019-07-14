@Override protected void onRestoreState(Bundle savedInstanceState){
  if (savedInstanceState == null) {
    return;
  }
  List<MediaBean> mediaList=savedInstanceState.getParcelableArrayList(EXTRA_MEDIA_LIST);
  mItemClickPosition=savedInstanceState.getInt(EXTRA_ITEM_CLICK_POSITION);
  if (mediaList != null) {
    mMediaBeanList.clear();
    Logger.i("????:" + mediaList.size() + "  d=" + mediaList.get(0).getOriginalPath());
    mMediaBeanList.addAll(mediaList);
  }
  mViewPager.setCurrentItem(mItemClickPosition);
  mMediaPreviewAdapter.notifyDataSetChanged();
}
