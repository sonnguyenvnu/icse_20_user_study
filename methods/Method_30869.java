public void loadImage(Uri imageUri){
  ImageUtils.loadImage(mImageView,imageUri);
  String type=UriUtils.getType(imageUri,getContext());
  boolean isGif=TextUtils.equals(type,"image/gif");
  ViewUtils.setVisibleOrGone(mGifImage,isGif);
}
