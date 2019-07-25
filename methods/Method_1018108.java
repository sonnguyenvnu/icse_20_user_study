@Override public boolean validate(Object value){
  if (value == null) {
    return false;
  }
  if (!allowEmptyString && value instanceof String) {
    String svalue=(trimString ? StringUtils.trim((String)value) : (String)value);
    return (!StringUtils.isEmpty(svalue));
  }
  return true;
}
