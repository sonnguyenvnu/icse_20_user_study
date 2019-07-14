public String getAsShortText(int fieldValue,Locale locale){
  return GJLocaleSymbols.forLocale(locale).monthOfYearValueToShortText(fieldValue);
}
