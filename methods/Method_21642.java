private boolean insertFilterIfExistsAfter(AggregationBuilder agg,List<Field> groupBy,AggregationBuilder builder,int nextPosition) throws SqlParseException {
  if (groupBy.size() <= nextPosition)   return false;
  Field filterFieldCandidate=groupBy.get(nextPosition);
  if (!(filterFieldCandidate instanceof MethodField))   return false;
  MethodField methodField=(MethodField)filterFieldCandidate;
  if (!methodField.getName().toLowerCase().equals("filter"))   return false;
  builder.subAggregation(aggMaker.makeGroupAgg(filterFieldCandidate).subAggregation(agg));
  return true;
}
