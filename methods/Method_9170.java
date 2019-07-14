public void updateTextureImageView(){
  if (textureImageView == null) {
    return;
  }
  try {
    currentBitmap=Bitmaps.createBitmap(textureView.getWidth(),textureView.getHeight(),Bitmap.Config.ARGB_8888);
    changedTextureView.getBitmap(currentBitmap);
  }
 catch (  Throwable e) {
    if (currentBitmap != null) {
      currentBitmap.recycle();
      currentBitmap=null;
    }
    FileLog.e(e);
  }
  if (currentBitmap != null) {
    textureImageView.setVisibility(VISIBLE);
    textureImageView.setImageBitmap(currentBitmap);
  }
 else {
    textureImageView.setImageDrawable(null);
  }
}
