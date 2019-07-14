/** 
 * Get the Users\name\AppData\Local path as a settings fallback. 
 */
static private String getLocalAppDataPath() throws Exception {
  return Shell32Util.getSpecialFolderPath(ShlObj.CSIDL_LOCAL_APPDATA,true);
}
