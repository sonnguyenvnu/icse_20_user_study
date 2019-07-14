private Map<StatisticMetric,BigDecimal> createStatisticMetrics(Set<ItemMetric> incomes,Set<ItemMetric> expenses,Saving saving){
  BigDecimal savingAmount=ratesService.convert(saving.getCurrency(),Currency.getBase(),saving.getAmount());
  BigDecimal expensesAmount=expenses.stream().map(ItemMetric::getAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
  BigDecimal incomesAmount=incomes.stream().map(ItemMetric::getAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
  return ImmutableMap.of(StatisticMetric.EXPENSES_AMOUNT,expensesAmount,StatisticMetric.INCOMES_AMOUNT,incomesAmount,StatisticMetric.SAVING_AMOUNT,savingAmount);
}
