/** 
 * Writes a simple bitmap to local storage. The image is a solid color with size 400x400
 */
private static void writeSolidColorBitmapToFile(File file,int color) throws IOException {
  if (!file.exists()) {
    Bitmap bitmap=Bitmap.createBitmap(400,400,Bitmap.Config.ARGB_8888);
    bitmap.eraseColor(color);
    FileOutputStream fos=null;
    try {
      fos=new FileOutputStream(file);
      bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
    }
  finally {
      if (fos != null) {
        fos.close();
      }
    }
  }
}
