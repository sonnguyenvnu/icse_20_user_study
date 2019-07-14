public BigDecimal getTotal(String currency){
  return Optional.ofNullable(additionalProperties.get(PREFIX_TOTAL + currency)).map(BigDecimal::new).orElse(BigDecimal.ZERO);
}
