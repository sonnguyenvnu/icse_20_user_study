@Override public void onRequestMediaCallback(List<MediaBean> list){
  if (!mConfiguration.isHideCamera()) {
    if (mPage == 1 && TextUtils.equals(mBucketId,String.valueOf(Integer.MIN_VALUE))) {
      MediaBean takePhotoBean=new MediaBean();
      takePhotoBean.setId(Integer.MIN_VALUE);
      takePhotoBean.setBucketId(String.valueOf(Integer.MIN_VALUE));
      mMediaBeanList.add(takePhotoBean);
    }
  }
  if (list != null && list.size() > 0) {
    mMediaBeanList.addAll(list);
    Logger.i(String.format("??:%s???",list.size()));
  }
 else {
    Logger.i("??????");
  }
  mMediaGridAdapter.notifyDataSetChanged();
  mPage++;
  if (list == null || list.size() < LIMIT) {
    mRvMedia.setFooterViewHide(true);
    mRvMedia.setHasLoadMore(false);
  }
 else {
    mRvMedia.setFooterViewHide(false);
    mRvMedia.setHasLoadMore(true);
  }
  if (mMediaBeanList.size() == 0) {
    String mediaEmptyTils=ThemeUtils.resolveString(getContext(),R.attr.gallery_media_empty_tips,R.string.gallery_default_media_empty_tips);
    EmptyViewUtils.showMessage(mLlEmptyView,mediaEmptyTils);
  }
  mRvMedia.onLoadMoreComplete();
}
