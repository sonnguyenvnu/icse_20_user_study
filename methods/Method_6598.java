public static String getSystemLocaleStringIso639(){
  Locale locale=getInstance().getSystemDefaultLocale();
  if (locale == null) {
    return "en";
  }
  String languageCode=locale.getLanguage();
  String countryCode=locale.getCountry();
  String variantCode=locale.getVariant();
  if (languageCode.length() == 0 && countryCode.length() == 0) {
    return "en";
  }
  StringBuilder result=new StringBuilder(11);
  result.append(languageCode);
  if (countryCode.length() > 0 || variantCode.length() > 0) {
    result.append('-');
  }
  result.append(countryCode);
  if (variantCode.length() > 0) {
    result.append('_');
  }
  result.append(variantCode);
  return result.toString();
}
