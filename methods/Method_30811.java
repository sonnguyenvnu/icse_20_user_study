public void loadImage(SizedImageItem image){
  ImageUtils.loadImageWithRatio(mImageView,image);
  ViewUtils.setVisibleOrGone(mGifImage,image.isAnimated());
}
