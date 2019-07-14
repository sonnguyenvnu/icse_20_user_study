public void hideRvBucketView(){
  if (slideOutUnderneathAnimation == null) {
    slideOutUnderneathAnimation=new SlideOutUnderneathAnimation(mRvBucket);
  }
  slideOutUnderneathAnimation.setDirection(Animation.DIRECTION_DOWN).setDuration(Animation.DURATION_DEFAULT).setListener(animation -> {
    mTvFolderName.setEnabled(true);
    mRlBucektOverview.setVisibility(View.GONE);
  }
).animate();
}
