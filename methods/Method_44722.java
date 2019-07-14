public Map<String,BigDecimal> getExchangeBalance(){
  return exchange != null ? Collections.unmodifiableMap(exchange) : Collections.emptyMap();
}
