protected void processValue(Object value){
  if (ExpressionUtils.isError(value)) {
    hasError=true;
  }
 else   if (ExpressionUtils.isNonBlankData(value)) {
    if (value instanceof OffsetDateTime) {
      long t=((OffsetDateTime)value).toInstant().toEpochMilli();
      hasTime=true;
      int bin=(int)Math.floor((double)(t - _index.getMin()) / (double)_index.getStep());
      if (bin >= 0 && bin < bins.length) {
        bins[bin]++;
      }
    }
 else {
      hasNonTime=true;
    }
  }
 else {
    hasBlank=true;
  }
}
