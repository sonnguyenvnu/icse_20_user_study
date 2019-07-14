private AggregationBuilder updateAggIfNested(AggregationBuilder lastAgg,Field field){
  if (field.isNested()) {
    lastAgg=AggregationBuilders.nested(field.getName() + "Nested",field.getNestedPath()).subAggregation(lastAgg);
  }
  return lastAgg;
}
