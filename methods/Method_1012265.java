public void release(){
  mISimpleTarget=null;
  if (mImageView != null) {
    mImageView.setImageBitmap(null);
    mImageView.setOnViewTapListener(null);
    mImageView.setOnPhotoTapListener(null);
    mImageView.setAlphaChangeListener(null);
    mImageView.setTransformOutListener(null);
    mImageView.transformIn(null);
    mImageView.transformOut(null);
    mImageView.setOnLongClickListener(null);
    mBtnVideo.setOnClickListener(null);
    mImageView=null;
    mRootView=null;
    isTransPhoto=false;
  }
}
