public BigDecimal getAveragePrice(){
  return low.add(high).divide(new BigDecimal("2"));
}
