private static Date convertBigDecimalTimestampToDate(BigDecimal timestamp){
  BigDecimal timestampInMillis=timestamp.multiply(new BigDecimal("1000"));
  return new Date(timestampInMillis.longValue());
}
