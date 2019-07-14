protected boolean isValueBlank(Object value){
  return value == null || (value instanceof String && ((String)value).trim().length() == 0);
}
