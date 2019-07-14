/** 
 * Finds message in default bundles only, starting from fallback bundlename.
 */
public String findDefaultMessage(final Locale locale,final String key){
  String indexedKey=calcIndexKey(key);
  String msg=getMessage(fallbackBundlename,locale,key,indexedKey);
  if (msg != null) {
    return msg;
  }
  for (  String bname : defaultBundles) {
    msg=getMessage(bname,locale,key,indexedKey);
    if (msg != null) {
      return msg;
    }
  }
  return null;
}
