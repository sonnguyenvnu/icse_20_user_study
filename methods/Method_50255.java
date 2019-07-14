@Override protected void onRestoreInstanceState(Bundle savedInstanceState){
  super.onRestoreInstanceState(savedInstanceState);
  List<MediaBean> list=savedInstanceState.getParcelableArrayList(EXTRA_CHECKED_LIST);
  if (list != null && list.size() > 0) {
    mCheckedList.clear();
    mCheckedList.addAll(list);
  }
  mPageMediaList=savedInstanceState.getParcelableArrayList(EXTRA_PAGE_MEDIA_LIST);
  mPagePosition=savedInstanceState.getInt(EXTRA_PAGE_POSITION);
  mPreviewPosition=savedInstanceState.getInt(EXTRA_PREVIEW_POSITION);
  mSelectedIndex=savedInstanceState.getInt(EXTRA_SELECTED_INDEX);
  if (!mConfiguration.isRadio()) {
switch (mSelectedIndex) {
case 1:
      showMediaPageFragment(mPageMediaList,mPagePosition);
    break;
case 2:
  showMediaPreviewFragment();
break;
}
}
}
