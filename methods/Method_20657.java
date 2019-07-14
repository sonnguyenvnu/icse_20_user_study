/** 
 * Returns a currency string appropriate to the user's locale and preferred currency.
 * @param initialValue Value to convert, local to the project's currency.
 * @param project The project to use to look up currency information.
 * @param roundingMode This determines whether we should round the values down or up.
 * @param precision How much of the change we should show.
 */
public String formatWithUserPreference(final double initialValue,final @NonNull Project project,final @NonNull RoundingMode roundingMode,final int precision){
  final Country country=Country.findByCurrencyCode(project.currentCurrency());
  if (country == null) {
    return "";
  }
  final float convertedValue=getRoundedValue(initialValue,roundingMode) * project.fxRate();
  final CurrencyOptions currencyOptions=currencyOptions(convertedValue,country,true);
  final NumberOptions numberOptions=NumberOptions.builder().currencySymbol(currencyOptions.currencySymbol()).roundingMode(roundingMode).precision(precision > 0 ? 2 : 0).build();
  return StringUtils.trim(NumberUtils.format(currencyOptions.value(),numberOptions));
}
