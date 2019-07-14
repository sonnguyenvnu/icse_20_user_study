/** 
 * Normalizes given item amount to  {@link Currency#getBase()} currency with{@link TimePeriod#getBase()} time period
 */
private ItemMetric createItemMetric(Item item){
  BigDecimal amount=ratesService.convert(item.getCurrency(),Currency.getBase(),item.getAmount()).divide(item.getPeriod().getBaseRatio(),4,RoundingMode.HALF_UP);
  return new ItemMetric(item.getTitle(),amount);
}
