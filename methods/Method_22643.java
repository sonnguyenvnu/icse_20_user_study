/** 
 * Get the Users\name\AppData\Roaming path to write settings files. 
 */
static private String getAppDataPath() throws Exception {
  return Shell32Util.getSpecialFolderPath(ShlObj.CSIDL_APPDATA,true);
}
