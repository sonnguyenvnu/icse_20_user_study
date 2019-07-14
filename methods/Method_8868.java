public Bitmap getBitmap(){
  if (cropView != null) {
    return cropView.getResult();
  }
  return null;
}
