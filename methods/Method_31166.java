private Boolean getBooleanProp(Map<String,String> props,String key){
  String value=props.remove(key);
  if (value != null && !"true".equals(value) && !"false".equals(value)) {
    throw new FlywayException("Invalid value for " + key + " (should be either true or false): " + value);
  }
  return value == null ? null : Boolean.valueOf(value);
}
