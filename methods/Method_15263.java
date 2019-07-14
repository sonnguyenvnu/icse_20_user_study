/** 
 * ??Sdcard????
 * @return
 */
public static boolean isExitsSdcard(){
  return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
}
