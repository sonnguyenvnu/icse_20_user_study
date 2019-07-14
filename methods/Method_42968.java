private int numberOfDecimals(String value){
  return new BigDecimal(value).stripTrailingZeros().scale();
}
