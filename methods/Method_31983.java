/** 
 * Obtains the symbols for a locale.
 * @param locale  the locale, null returns default
 * @return the symbols, not null
 */
static GJLocaleSymbols forLocale(Locale locale){
  if (locale == null) {
    locale=Locale.getDefault();
  }
  GJLocaleSymbols symbols=cCache.get(locale);
  if (symbols == null) {
    symbols=new GJLocaleSymbols(locale);
    GJLocaleSymbols oldSymbols=cCache.putIfAbsent(locale,symbols);
    if (oldSymbols != null) {
      symbols=oldSymbols;
    }
  }
  return symbols;
}
