/** 
 * Method init
 * @param languageCode
 * @param countryCode
 */
public static synchronized void init(String languageCode,String countryCode){
  if (alreadyInitialized) {
    return;
  }
  I18n.resourceBundle=ResourceBundle.getBundle(Constants.exceptionMessagesResourceBundleBase,new Locale(languageCode,countryCode));
  alreadyInitialized=true;
}
