/** 
 * ??SharedPreferences????
 * @param fileName
 */
private static SharedPreferences getSharedPreference(String fileName){
  return CloudReaderApplication.getInstance().getSharedPreferences(fileName,Context.MODE_PRIVATE);
}
