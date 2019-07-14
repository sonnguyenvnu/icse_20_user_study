/** 
 * Returns the currency symbol for a country.
 * @param country The country the currency will be displayed in.
 * @param excludeCurrencyCode If true, hide the US currency code for US users only.
 */
public String getCurrencySymbol(final @NonNull Country country,final boolean excludeCurrencyCode){
  if (!currencyNeedsCode(country,excludeCurrencyCode)) {
    return country.getCurrencySymbol();
  }
 else   if (country == Country.SG) {
    return "\u00A0" + "S" + country.getCurrencySymbol() + "\u00A0";
  }
 else   if (country.getCurrencySymbol().equals("kr") || country.getCurrencySymbol().equals("Fr")) {
    return "\u00A0" + country.getCurrencyCode() + "\u00A0";
  }
 else {
    return "\u00A0" + country.getCountryCode() + country.getCurrencySymbol() + "\u00A0";
  }
}
