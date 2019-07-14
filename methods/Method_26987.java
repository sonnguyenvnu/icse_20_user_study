private void prepareImageReader(){
  if (mImageReader != null) {
    mImageReader.close();
  }
  Size largest=mPictureSizes.sizes(mAspectRatio).last();
  mImageReader=ImageReader.newInstance(largest.getWidth(),largest.getHeight(),ImageFormat.JPEG,2);
  mImageReader.setOnImageAvailableListener(mOnImageAvailableListener,null);
}
