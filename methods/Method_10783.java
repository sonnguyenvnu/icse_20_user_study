/** 
 * ?Uri???File
 * @param context
 * @param uri
 * @return
 */
public static File getFilePhotoFromUri(Activity context,Uri uri){
  return new File(RxPhotoTool.getImageAbsolutePath(context,uri));
}
