/** 
 * {@inheritDoc}
 */
@Override public Map<Currency,BigDecimal> getCurrentRates(){
  if (container == null || !container.getDate().equals(LocalDate.now())) {
    container=client.getRates(Currency.getBase());
    log.info("exchange rates has been updated: {}",container);
  }
  return ImmutableMap.of(Currency.EUR,container.getRates().get(Currency.EUR.name()),Currency.RUB,container.getRates().get(Currency.RUB.name()),Currency.USD,BigDecimal.ONE);
}
