/** 
 * Returns a currency string appropriate to the user's locale and location relative to a project.
 * @param initialValue        Value to display, local to the project's currency.
 * @param project             The project to use to look up currency information.
 * @param excludeCurrencyCode If true, hide the US currency code for US users only.
 */
public @NonNull String format(final double initialValue,final @NonNull Project project,final boolean excludeCurrencyCode,final @NonNull RoundingMode roundingMode){
  final Country country=Country.findByCurrencyCode(project.currency());
  if (country == null) {
    return "";
  }
  final float roundedValue=getRoundedValue(initialValue,roundingMode);
  final CurrencyOptions currencyOptions=currencyOptions(roundedValue,country,excludeCurrencyCode);
  final NumberOptions numberOptions=NumberOptions.builder().currencyCode(currencyOptions.currencyCode()).currencySymbol(currencyOptions.currencySymbol()).roundingMode(roundingMode).precision(getPrecision(initialValue,roundingMode)).build();
  return StringUtils.trim(NumberUtils.format(currencyOptions.value(),numberOptions));
}
