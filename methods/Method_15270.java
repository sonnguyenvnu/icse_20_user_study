/** 
 * ?????SD?
 */
public static boolean hasSDCard(){
  return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
}
