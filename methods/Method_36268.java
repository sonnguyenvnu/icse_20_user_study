public void showImage(final Uri thumbnail,final Uri uri){
  mThumbnail=thumbnail;
  mUri=uri;
  clearThumbnailAndProgressIndicator();
  mImageLoader.loadImage(hashCode(),uri,mInternalCallback);
  if (mFailureImageView != null) {
    mFailureImageView.setVisibility(GONE);
  }
}
