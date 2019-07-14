protected void processValue(Object value){
  if (ExpressionUtils.isError(value)) {
    hasError=true;
  }
 else   if (ExpressionUtils.isNonBlankData(value)) {
    if (value instanceof Number) {
      double d=((Number)value).doubleValue();
      if (!Double.isInfinite(d) && !Double.isNaN(d)) {
        hasNumeric=true;
        int bin=(int)Math.floor((d - _index.getMin()) / _index.getStep());
        if (bin >= 0 && bin < bins.length) {
          bins[bin]++;
        }
      }
 else {
        hasError=true;
      }
    }
 else {
      hasNonNumeric=true;
    }
  }
 else {
    hasBlank=true;
  }
}
