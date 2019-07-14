/** 
 * ????url?path path??URL_SUFFIX_SMALL
 * @param uri
 * @param isLocalPath
 * @return
 */
public static String getSmallUri(String uri,boolean isLocalPath){
  if (uri == null) {
    Log.e(TAG,"getSmallUri  uri == null >> return null;");
    return null;
  }
  if (uri.startsWith("/") || uri.startsWith(FILE_PATH_PREFIX) || StringUtil.isFilePathExist(FILE_PATH_PREFIX + uri)) {
    isLocalPath=true;
  }
  return isLocalPath || uri.endsWith(URL_SUFFIX_SMALL) ? uri : uri + URL_SUFFIX_SMALL;
}
