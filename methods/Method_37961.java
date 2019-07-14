/** 
 * Gets the message from the named resource bundle. Performs the failback only when bundle name or locale are not specified (i.e. are <code>null</code>).
 */
public String getMessage(final String bundleName,final Locale locale,final String key){
  ResourceBundle bundle=findResourceBundle(bundleName,locale);
  if (bundle == null) {
    return null;
  }
  try {
    return bundle.getString(key);
  }
 catch (  MissingResourceException mrex) {
    return null;
  }
}
