/** 
 * ??????????????
 */
public static int getImageOrientation(String uri) throws IOException {
  if (!new File(uri).exists()) {
    return 0;
  }
  return new ExifInterface(uri).getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL);
}
