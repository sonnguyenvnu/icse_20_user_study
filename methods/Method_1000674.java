/** 
 * ??????????? ~ ????????
 * @param path ??
 * @param enc ??????
 * @return ???????
 */
public static String normalize(String path,String enc){
  if (Strings.isEmpty(path))   return null;
  if (path.charAt(0) == '~')   path=Disks.home() + path.substring(1);
  try {
    return URLDecoder.decode(path,enc);
  }
 catch (  UnsupportedEncodingException e) {
    return null;
  }
}
