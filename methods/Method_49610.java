private Query getNumericRangeQuery(final String field,final Class<?> type,String start,String end,final boolean includeLower,final boolean includeUpper){
  if (AttributeUtil.isWholeNumber(type)) {
    long min=isMatchAll(start) ? Long.MIN_VALUE : Long.parseLong(start);
    long max=isMatchAll(end) ? Long.MAX_VALUE : Long.parseLong(end);
    if (!includeLower) {
      min=Math.addExact(min,1);
    }
    if (!includeUpper) {
      max=Math.addExact(max,-1);
    }
    return LongPoint.newRangeQuery(field,min,max);
  }
 else {
    double min=isMatchAll(start) ? Double.NEGATIVE_INFINITY : Double.parseDouble(start);
    double max=isMatchAll(end) ? Double.POSITIVE_INFINITY : Double.parseDouble(end);
    if (!includeLower) {
      min=DoublePoint.nextUp(min);
    }
    if (!includeUpper) {
      max=DoublePoint.nextDown(max);
    }
    return DoublePoint.newRangeQuery(field,min,max);
  }
}
