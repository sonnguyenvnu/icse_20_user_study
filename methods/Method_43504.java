protected String getLocalizedBigDecimalValue(BigDecimal input){
  final NumberFormat numberFormat=NumberFormat.getNumberInstance(Locale.US);
  numberFormat.setGroupingUsed(true);
  numberFormat.setMaximumFractionDigits(8);
  numberFormat.setMinimumFractionDigits(2);
  return numberFormat.format(input);
}
