public static ResourceBundle changeLanguage(String language){
  Locale locale=Locale.CHINESE.getLanguage().equals(language) ? Locale.CHINESE : Locale.ENGLISH;
  return changeLanguage(locale);
}
