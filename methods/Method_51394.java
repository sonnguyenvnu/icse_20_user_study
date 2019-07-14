@Override public File createFrom(String propertyString){
  return StringUtils.isBlank(propertyString) ? null : new File(propertyString);
}
