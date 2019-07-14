private AggregationBuilder wrapNestedIfNeeded(AggregationBuilder nestedBuilder,boolean reverseNested){
  if (!reverseNested)   return nestedBuilder;
  if (reverseNested && !(nestedBuilder instanceof NestedAggregationBuilder))   return nestedBuilder;
  return AggregationBuilders.reverseNested(nestedBuilder.getName() + "_REVERSED").subAggregation(nestedBuilder);
}
