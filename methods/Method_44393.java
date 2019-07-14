private static DecimalFormat getCryptoFormat(){
  DecimalFormat cryptoFormat=new DecimalFormat();
  cryptoFormat.setDecimalFormatSymbols(CUSTOM_SYMBOLS);
  cryptoFormat.setMaximumFractionDigits(4);
  cryptoFormat.setGroupingUsed(false);
  cryptoFormat.setRoundingMode(RoundingMode.DOWN);
  return cryptoFormat;
}
