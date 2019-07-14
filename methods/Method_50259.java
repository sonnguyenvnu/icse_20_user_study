private void backAction(){
  if (mMediaGridFragment != null && mMediaGridFragment.isShowRvBucketView()) {
    mMediaGridFragment.hideRvBucketView();
    return;
  }
  if ((mMediaPreviewFragment != null && mMediaPreviewFragment.isVisible()) || (mMediaPageFragment != null && mMediaPageFragment.isVisible())) {
    showMediaGridFragment();
    return;
  }
  onBackPressed();
}
