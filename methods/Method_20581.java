/** 
 * A currency needs a code if its symbol is ambiguous, e.g. `$` is used for currencies such as USD, CAD, AUD.
 */
public boolean currencyNeedsCode(final @NonNull String currencySymbol){
  for (  final LaunchedCountry country : launchedCountries()) {
    if (country.currencySymbol().equals(currencySymbol)) {
      return country.trailingCode();
    }
  }
  return true;
}
