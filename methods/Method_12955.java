public static String formatFloat(String value){
  if (null != value && value.contains(".")) {
    if (isNumeric(value)) {
      try {
        BigDecimal decimal=new BigDecimal(value);
        BigDecimal setScale=decimal.setScale(10,BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros();
        return setScale.toPlainString();
      }
 catch (      Exception e) {
      }
    }
  }
  return value;
}
