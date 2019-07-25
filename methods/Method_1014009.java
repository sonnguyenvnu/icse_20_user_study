@Override public String format(@Nullable String pattern){
  String formatPattern=pattern;
  if (formatPattern != null && formatPattern.contains(UnitUtils.UNIT_PLACEHOLDER)) {
    String unitSymbol=getUnit().equals(SmartHomeUnits.PERCENT) ? "%%" : getUnit().toString();
    formatPattern=formatPattern.replace(UnitUtils.UNIT_PLACEHOLDER,unitSymbol);
  }
  try {
    return String.format(formatPattern,toBigDecimal().toBigIntegerExact());
  }
 catch (  ArithmeticException ae) {
  }
catch (  IllegalFormatConversionException ifce) {
  }
  return String.format(formatPattern,toBigDecimal());
}
