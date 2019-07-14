/** 
 * ??bitmap
 * @param filePath ????
 * @return bitmap
 */
public static Bitmap getBitmap(String filePath){
  if (RxDataTool.isNullString(filePath)) {
    return null;
  }
  return BitmapFactory.decodeFile(filePath);
}
