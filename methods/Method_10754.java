/** 
 * ??SD?Data??
 * @return SD?Data??
 */
public static String getDataPath(){
  if (!isSDCardEnable()) {
    return "sdcard unable!";
  }
  return Environment.getDataDirectory().getPath();
}
