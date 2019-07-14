/** 
 * ??bitmap
 * @param file ??
 * @return bitmap
 */
public static Bitmap getBitmap(File file){
  if (file == null) {
    return null;
  }
  InputStream is=null;
  try {
    is=new BufferedInputStream(new FileInputStream(file));
    return BitmapFactory.decodeStream(is);
  }
 catch (  FileNotFoundException e) {
    e.printStackTrace();
    return null;
  }
 finally {
    RxFileTool.closeIO(is);
  }
}
