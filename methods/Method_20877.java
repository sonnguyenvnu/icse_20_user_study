/** 
 * Returns a SpannableString that shrinks currency code if it's necessary. Special case: US people looking at US currency just get the currency symbol.
 */
public static @NotNull SpannableString styleCurrency(final double value,final Project project,final @NonNull KSCurrency ksCurrency,final boolean centered){
  final String formattedCurrency=ksCurrency.format(value,project,RoundingMode.HALF_UP);
  final SpannableString spannableString=new SpannableString(formattedCurrency);
  final Country country=Country.findByCurrencyCode(project.currency());
  if (country == null) {
    return spannableString;
  }
  final boolean currencyNeedsCode=ksCurrency.currencyNeedsCode(country,true);
  final String currencySymbolToDisplay=StringUtils.trim(ksCurrency.getCurrencySymbol(country,true));
  if (currencyNeedsCode) {
    final int startOfSymbol=formattedCurrency.indexOf(currencySymbolToDisplay);
    final int endOfSymbol=startOfSymbol + currencySymbolToDisplay.length();
    if (centered) {
      spannableString.setSpan(new RelativeSizeSpan(.5f),startOfSymbol,endOfSymbol,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
      spannableString.setSpan(new CenterSpan(),startOfSymbol,endOfSymbol,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    }
 else {
      spannableString.setSpan(new RelativeSizeSpan(.7f),startOfSymbol,endOfSymbol,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    }
  }
  return spannableString;
}
