/** 
 * Return a formatter that can output an appropriate number based on the input currency and locale.
 */
private static @NonNull NumberFormat numberFormat(final @NonNull NumberOptions options,final @NonNull Locale locale){
  final NumberFormat numberFormat;
  if (options.isCurrency()) {
    final DecimalFormat decimalFormat=(DecimalFormat)NumberFormat.getCurrencyInstance(locale);
    final DecimalFormatSymbols symbols=decimalFormat.getDecimalFormatSymbols();
    symbols.setCurrencySymbol(options.currencySymbol());
    decimalFormat.setDecimalFormatSymbols(symbols);
    numberFormat=decimalFormat;
  }
 else {
    numberFormat=NumberFormat.getInstance(locale);
  }
  return numberFormat;
}
