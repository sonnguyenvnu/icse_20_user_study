@UiThread private void doShowImage(final int imageType,final File image){
  if (mMainView != null) {
    removeView(mMainView);
  }
  mMainView=mViewFactory.createMainView(getContext(),imageType,image,mInitScaleType);
  if (mMainView == null) {
    onFail(new RuntimeException("Image type not supported: " + ImageInfoExtractor.typeName(imageType)));
    return;
  }
  addView(mMainView,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
  mMainView.setOnClickListener(mOnClickListener);
  mMainView.setOnLongClickListener(mOnLongClickListener);
  if (mMainView instanceof SubsamplingScaleImageView) {
    mSSIV=(SubsamplingScaleImageView)mMainView;
    mSSIV.setMinimumTileDpi(160);
    setOptimizeDisplay(mOptimizeDisplay);
    setInitScaleType(mInitScaleType);
    mSSIV.setImage(ImageSource.uri(Uri.fromFile(image)));
  }
  if (mFailureImageView != null) {
    mFailureImageView.setVisibility(GONE);
  }
}
