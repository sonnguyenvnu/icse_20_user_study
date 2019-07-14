private void saveLastCameraBitmap(){
  Bitmap bitmap=textureView.getBitmap();
  if (bitmap != null) {
    lastBitmap=Bitmap.createScaledBitmap(textureView.getBitmap(),80,80,true);
    if (lastBitmap != null) {
      Utilities.blurBitmap(lastBitmap,7,1,lastBitmap.getWidth(),lastBitmap.getHeight(),lastBitmap.getRowBytes());
      try {
        File file=new File(ApplicationLoader.getFilesDirFixed(),"icthumb.jpg");
        FileOutputStream stream=new FileOutputStream(file);
        lastBitmap.compress(Bitmap.CompressFormat.JPEG,87,stream);
      }
 catch (      Throwable ignore) {
      }
    }
  }
}
