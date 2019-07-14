/** 
 * Returns a boolean determining if a country's currency is ambiguous. Special case: US people looking at US currency just get the currency symbol.
 * @param country The country to check if a code is necessary.
 * @param excludeCurrencyCode If true, hide the US currency code for US users only.
 */
public boolean currencyNeedsCode(final @NonNull Country country,final boolean excludeCurrencyCode){
  final boolean countryIsUS=country == Country.US;
  final Config config=this.currentConfig.getConfig();
  final boolean currencyNeedsCode=config.currencyNeedsCode(country.getCurrencySymbol());
  final boolean userInUS=config.countryCode().equals(Country.US.getCountryCode());
  if (userInUS && excludeCurrencyCode && countryIsUS) {
    return false;
  }
 else {
    return currencyNeedsCode;
  }
}
