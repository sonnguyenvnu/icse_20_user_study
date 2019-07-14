/** 
 * Build  {@link CurrencyOptions} based on the country.
 */
private @NonNull CurrencyOptions currencyOptions(final float value,final @NonNull Country country,final boolean excludeCurrencyCode){
  return CurrencyOptions.builder().country(country.getCountryCode()).currencyCode("").currencySymbol(getCurrencySymbol(country,excludeCurrencyCode)).value(value).build();
}
