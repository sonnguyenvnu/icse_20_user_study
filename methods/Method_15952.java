@Override @SuppressWarnings("unchecked") public <T>T getProperty(String propertyName,T defaultValue){
  if (null == properties) {
    return defaultValue;
  }
  return (T)properties.getOrDefault(propertyName,defaultValue);
}
