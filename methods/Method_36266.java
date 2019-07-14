public void setFailureImage(Drawable failureImage){
  if (failureImage == null) {
    return;
  }
  if (mFailureImageView == null) {
    mFailureImageView=new ImageView(getContext());
    mFailureImageView.setVisibility(GONE);
    mFailureImageView.setOnClickListener(mFailureImageClickListener);
    if (mFailureImageScaleType != null) {
      mFailureImageView.setScaleType(mFailureImageScaleType);
    }
    addView(mFailureImageView);
  }
  mFailureImageView.setImageDrawable(failureImage);
}
