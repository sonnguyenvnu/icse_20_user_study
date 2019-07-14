private Object covenValue(Aggregation value) throws SqlParseException {
  if (value instanceof InternalNumericMetricsAggregation.SingleValue) {
    return ((InternalNumericMetricsAggregation.SingleValue)value).value();
  }
 else   if (value instanceof InternalValueCount) {
    return ((InternalValueCount)value).getValue();
  }
 else   if (value instanceof InternalTopHits) {
    return (value);
  }
 else   if (value instanceof LongTerms) {
    return value;
  }
 else {
    throw new SqlParseException("unknow this agg type " + value.getClass());
  }
}
