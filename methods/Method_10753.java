/** 
 * ??SD?????
 * @return true : ??<br>false : ???
 */
public static boolean isSDCardEnable(){
  return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
}
