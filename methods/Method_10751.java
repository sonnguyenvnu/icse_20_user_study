/** 
 * ??SD????.
 */
public static File getRootPath(){
  File path=null;
  if (sdCardIsAvailable()) {
    path=Environment.getExternalStorageDirectory();
  }
 else {
    path=Environment.getDataDirectory();
  }
  return path;
}
