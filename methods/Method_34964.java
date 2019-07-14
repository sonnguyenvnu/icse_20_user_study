private void setupThumbnail(int targetWidth,int targetHeight){
  thumbnail.setMaxWidth(targetWidth);
  thumbnail.setMaxHeight(targetHeight);
  thumbnail.setMinimumWidth(targetWidth);
  thumbnail.setMinimumHeight(targetHeight);
  thumbnail.requestLayout();
}
