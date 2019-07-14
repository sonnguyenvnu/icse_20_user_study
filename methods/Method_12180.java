public static BigDecimal amountFormat(BigDecimal number){
  number=number.setScale(BidConst.STORE_SCALE,RoundingMode.HALF_UP);
  return number;
}
