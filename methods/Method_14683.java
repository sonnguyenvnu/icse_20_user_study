@Override public KeyMaker createKeyMaker(){
  return new KeyMaker(){
    @Override protected Object makeKey(    Object value){
      if (ExpressionUtils.isNonBlankData(value)) {
        if (value instanceof Number) {
          return value;
        }
 else         if (value instanceof Boolean) {
          return ((Boolean)value).booleanValue() ? 1 : 0;
        }
 else         if (value instanceof OffsetDateTime) {
          return ((OffsetDateTime)value).toInstant().toEpochMilli();
        }
 else         if (value instanceof String) {
          try {
            double d=Double.parseDouble((String)value);
            if (!Double.isNaN(d)) {
              return d;
            }
          }
 catch (          NumberFormatException e) {
          }
        }
        return s_error;
      }
      return null;
    }
    @Override public int compareKeys(    Object key1,    Object key2){
      double d1=((Number)key1).doubleValue();
      double d2=((Number)key2).doubleValue();
      return d1 < d2 ? -1 : (d1 > d2 ? 1 : 0);
    }
  }
;
}
