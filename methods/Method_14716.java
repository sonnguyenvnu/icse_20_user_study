/** 
 * For Windows file paths that contain user IDs with non ASCII characters, those characters might get replaced with ?. We need to use the environment APPDATA value to substitute back the original user ID.
 */
static private String fixWindowsUnicodePath(String path){
  int q=path.indexOf('?');
  if (q < 0) {
    return path;
  }
  int pathSep=path.indexOf(File.separatorChar,q);
  String goodPath=System.getenv("APPDATA");
  if (goodPath == null || goodPath.length() == 0) {
    goodPath=System.getenv("USERPROFILE");
    if (!goodPath.endsWith(File.separator)) {
      goodPath=goodPath + File.separator;
    }
  }
  int goodPathSep=goodPath.indexOf(File.separatorChar,q);
  return path.substring(0,q) + goodPath.substring(q,goodPathSep) + path.substring(pathSep);
}
