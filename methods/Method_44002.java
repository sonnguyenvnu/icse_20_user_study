public BigDecimal getQuantity(){
  return filledSize.add(unfilledSize);
}
