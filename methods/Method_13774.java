/** 
 * {@inheritDoc}
 */
@Override public DataPoint save(String accountName,Account account){
  Instant instant=LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
  DataPointId pointId=new DataPointId(accountName,Date.from(instant));
  Set<ItemMetric> incomes=account.getIncomes().stream().map(this::createItemMetric).collect(Collectors.toSet());
  Set<ItemMetric> expenses=account.getExpenses().stream().map(this::createItemMetric).collect(Collectors.toSet());
  Map<StatisticMetric,BigDecimal> statistics=createStatisticMetrics(incomes,expenses,account.getSaving());
  DataPoint dataPoint=new DataPoint();
  dataPoint.setId(pointId);
  dataPoint.setIncomes(incomes);
  dataPoint.setExpenses(expenses);
  dataPoint.setStatistics(statistics);
  dataPoint.setRates(ratesService.getCurrentRates());
  log.debug("new datapoint has been created: {}",pointId);
  return repository.save(dataPoint);
}
