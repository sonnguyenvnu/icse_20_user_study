public Bitmap getVisibleRectangleBitmap(){
  ImageView imageView=getImageView();
  return imageView == null ? null : imageView.getDrawingCache();
}
