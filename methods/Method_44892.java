public BigDecimal calcBid(){
  return bid.add(BASIS_POINT_MULTIPLIER.multiply(bidBP));
}
