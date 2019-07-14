@Override public Boolean convert(Object value){
  if (value instanceof Number) {
    return decode(((Number)value).byteValue());
  }
 else   if (value instanceof String) {
    return Boolean.parseBoolean((String)value);
  }
 else   return null;
}
