/** 
 * ??????
 * @param path
 * @param name
 * @return
 */
public static String getAbsPath(String path,String name){
  Log.i(TAG,"getPath  path = " + path + "; name = " + name + " <<<<<<<<<<<<<");
  path=StringUtil.getString(path);
  name=StringUtil.getString(name);
  if (StringUtil.isNotEmpty(path,false)) {
    if (StringUtil.isNotEmpty(name,false)) {
      path+=((name.startsWith("/") ? "" : "/") + name);
    }
  }
 else {
    path=name;
  }
  if (path.startsWith("/")) {
    path=path.substring(1);
  }
  Log.i(TAG,"getPath  return " + path + " >>>>>>>>>>>>>>>>");
  return path;
}
