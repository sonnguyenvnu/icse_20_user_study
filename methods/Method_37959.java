/** 
 * Finds messages in the provided bundle. If message not found, all parent bundles will be examined until the root bundle. At the end, if still no success, all default bundles will be examined. Returns <code>null</code> if key is not found.
 */
public String findMessage(String bundleName,final Locale locale,final String key){
  String indexedKey=calcIndexKey(key);
  String name=bundleName;
  while (true) {
    String msg=getMessage(name,locale,key,indexedKey);
    if (msg != null) {
      return msg;
    }
    if (bundleName == null || bundleName.length() == 0) {
      break;
    }
    int ndx=bundleName.lastIndexOf('.');
    if (ndx == -1) {
      bundleName=null;
      name=fallbackBundlename;
    }
 else {
      bundleName=bundleName.substring(0,ndx);
      name=bundleName + '.' + fallbackBundlename;
    }
  }
  for (  String bname : defaultBundles) {
    String msg=getMessage(bname,locale,key,indexedKey);
    if (msg != null) {
      return msg;
    }
  }
  return null;
}
