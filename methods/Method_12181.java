public static BigDecimal rateFormat(BigDecimal number){
  number=number.setScale(BidConst.STORE_SCALE,RoundingMode.HALF_UP);
  return number;
}
