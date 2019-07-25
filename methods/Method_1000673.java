/** 
 * ????????????????????????null
 * @param path ??
 * @param klassLoader ?? ClassLoader
 * @param enc ??????
 * @return ????
 */
public static String absolute(String path,ClassLoader klassLoader,String enc){
  path=normalize(path,enc);
  if (Strings.isEmpty(path))   return null;
  File f=new File(path);
  if (!f.exists()) {
    URL url=null;
    try {
      url=klassLoader.getResource(path);
      if (null == url)       url=Thread.currentThread().getContextClassLoader().getResource(path);
      if (null == url)       url=ClassLoader.getSystemResource(path);
    }
 catch (    Throwable e) {
    }
    if (null != url)     return normalize(url.getPath(),Encoding.UTF8);
    return null;
  }
  return path;
}
