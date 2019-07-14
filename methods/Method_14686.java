@Override public KeyMaker createKeyMaker(){
  return new KeyMaker(){
    @Override protected Object makeKey(    Object value){
      return collator.getCollationKey((ExpressionUtils.isNonBlankData(value) && !(value instanceof String)) ? value.toString() : (String)value);
    }
    @Override public int compareKeys(    Object key1,    Object key2){
      return ((CollationKey)key1).compareTo((CollationKey)key2);
    }
  }
;
}
