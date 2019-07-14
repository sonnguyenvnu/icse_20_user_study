/** 
 * ??bitmap
 * @param fd ????
 * @return bitmap
 */
public static Bitmap getBitmap(FileDescriptor fd){
  if (fd == null) {
    return null;
  }
  return BitmapFactory.decodeFileDescriptor(fd);
}
