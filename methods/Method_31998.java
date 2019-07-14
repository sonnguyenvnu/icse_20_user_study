protected int convertText(String text,Locale locale){
  return GJLocaleSymbols.forLocale(locale).monthOfYearTextToValue(text);
}
