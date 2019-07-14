private Query buildNumericQuery(final String field,final String value,Class<?> type){
  if (AttributeUtil.isWholeNumber(type)) {
    if (isMatchAll(value)) {
      return LongPoint.newRangeQuery(field,Long.MIN_VALUE,Long.MAX_VALUE);
    }
 else {
      return LongPoint.newExactQuery(field,Long.parseLong(value));
    }
  }
 else {
    if (isMatchAll(value)) {
      return DoublePoint.newRangeQuery(field,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
    }
 else {
      return DoublePoint.newExactQuery(field,Double.parseDouble(value));
    }
  }
}
