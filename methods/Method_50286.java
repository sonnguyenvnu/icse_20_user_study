@Override public void onPageSelected(int position){
  mItemClickPosition=position;
  MediaBean mediaBean=mMediaBeanList.get(position);
  if (mMediaActivity != null && mMediaActivity.getCheckedList() != null) {
    mCbCheck.setChecked(mMediaActivity.getCheckedList().contains(mediaBean));
  }
 else {
    mCbCheck.setChecked(false);
  }
  RxBus.getDefault().post(new MediaViewPagerChangedEvent(position,mMediaBeanList.size(),false));
}
