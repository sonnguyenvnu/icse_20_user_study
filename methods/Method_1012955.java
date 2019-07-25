@Override public Map<String,Optional<BigDecimal>> check(Check check) throws Exception {
  Map<String,Optional<BigDecimal>> targetValues=new HashMap<String,Optional<BigDecimal>>();
  try {
    JsonNode node=graphiteHttpClient.getTargetJson(check.getTarget(),check.getFrom(),check.getUntil());
    for (    JsonNode metric : node) {
      String target=metric.path("target").asText();
      try {
        BigDecimal value=getLatestValue(metric);
        targetValues.put(target,Optional.of(value));
      }
 catch (      InvalidGraphiteValueException e) {
        LOGGER.warn("{} failed to read from Graphite",check.getName(),e);
        targetValues.put(target,Optional.<BigDecimal>absent());
      }
    }
  }
 catch (  GraphiteReadException e) {
    LOGGER.warn(check.getName() + " failed to read from Graphite",e);
  }
  return targetValues;
}
