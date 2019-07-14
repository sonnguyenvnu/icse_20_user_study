private void showImage(){
  String imageSize=(String)mImageSize.getSelectedItem();
  if (TextUtils.equals(imageSize,getString(R.string.size_small))) {
    mBigImageView.showImage(mSmallImage);
  }
 else   if (TextUtils.equals(imageSize,getString(R.string.size_medium))) {
    mBigImageView.showImage(mMediumImage);
  }
 else   if (TextUtils.equals(imageSize,getString(R.string.size_big))) {
    mBigImageView.showImage(mBigImage);
  }
}
