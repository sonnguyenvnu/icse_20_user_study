private static DecimalFormat getFiatFormat(){
  DecimalFormat fiatFormat=new DecimalFormat();
  fiatFormat.setDecimalFormatSymbols(CUSTOM_SYMBOLS);
  fiatFormat.setMaximumFractionDigits(2);
  fiatFormat.setGroupingUsed(false);
  fiatFormat.setRoundingMode(RoundingMode.DOWN);
  return fiatFormat;
}
