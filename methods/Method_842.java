public static ResourceBundle changeLanguage(Locale locale){
  if (currentLocale != null && currentLocale.equals(locale)) {
    return resourceBundle;
  }
  currentLocale=locale;
  resourceBundle=ResourceBundle.getBundle("messages",locale,new XmlControl());
  return resourceBundle;
}
