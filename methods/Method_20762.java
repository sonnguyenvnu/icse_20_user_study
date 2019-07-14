/** 
 * Returns the proper DateTime format pattern for supported locales.
 */
private static @NonNull String localePattern(final @NonNull Locale locale){
switch (locale.getLanguage()) {
case "de":
    return "MMMM yyyy";
case "en":
  return "MMMM yyyy";
case "es":
return "MMMM yyyy";
case "fr":
return "MMMM yyyy";
case "ja":
return "yyyy'?'MMMM";
default :
return "MMMM yyyy";
}
}
