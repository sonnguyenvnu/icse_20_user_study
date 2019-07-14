public BigDecimal getFrozen(String currency){
  return Optional.ofNullable(additionalProperties.get(PREFIX_FROZEN + currency)).map(BigDecimal::new).orElse(BigDecimal.ZERO);
}
