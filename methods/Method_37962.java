/** 
 * Finds resource bundle by it's name. Missed and founded resource bundles are cached for better performances. Returns <code>null</code> if resource bundle is missing.
 */
public ResourceBundle findResourceBundle(String bundleName,Locale locale){
  if (bundleName == null) {
    bundleName=fallbackBundlename;
  }
  if (locale == null) {
    locale=fallbackLocale;
  }
  if (!cacheResourceBundles) {
    try {
      return getBundle(bundleName,locale,ClassLoaderUtil.getDefaultClassLoader());
    }
 catch (    MissingResourceException ignore) {
      return null;
    }
  }
  String key=bundleName + '_' + locale.toLanguageTag();
  try {
    if (!misses.contains(key)) {
      ResourceBundle bundle=notmisses.get(key);
      if (bundle == null) {
        bundle=getBundle(bundleName,locale,ClassLoaderUtil.getDefaultClassLoader());
        notmisses.put(key,bundle);
      }
      return bundle;
    }
  }
 catch (  MissingResourceException ignore) {
    misses.add(key);
  }
  return null;
}
