@UiThread public void scrollToPositionWithOffset(int position,int offset){
  if (mMountedView == null || !(mMountedView.getLayoutManager() instanceof LinearLayoutManager)) {
    mCurrentFirstVisiblePosition=position;
    mCurrentOffset=offset;
    return;
  }
  ((LinearLayoutManager)mMountedView.getLayoutManager()).scrollToPositionWithOffset(position,offset);
}
