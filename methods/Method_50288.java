@Override public void onPageSelected(int position){
  mPagerPosition=position;
  MediaBean mediaBean=mMediaBeanList.get(position);
  mCbCheck.setChecked(false);
  if (mMediaActivity != null && mMediaActivity.getCheckedList() != null) {
    mCbCheck.setChecked(mMediaActivity.getCheckedList().contains(mediaBean));
  }
  RxBus.getDefault().post(new MediaViewPagerChangedEvent(position,mMediaBeanList.size(),true));
}
