public String getAsText(int fieldValue,Locale locale){
  return GJLocaleSymbols.forLocale(locale).monthOfYearValueToText(fieldValue);
}
