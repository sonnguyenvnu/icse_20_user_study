@Override public Map<String,Optional<BigDecimal>> check(Check check) throws Exception {
  return ImmutableMap.of(check.getTarget(),Optional.<BigDecimal>of(value));
}
