@Override public boolean validate(String value){
  if (StringUtils.isBlank(validationType)) {
    return true;
  }
  String trimmedValue=StringUtils.deleteWhitespace(value);
  if ("UPPERCASE".equalsIgnoreCase(validationType)) {
    return trimmedValue.toUpperCase().equals(trimmedValue);
  }
 else   if ("LOWERCASE".equalsIgnoreCase(validationType)) {
    return trimmedValue.toLowerCase().equals(trimmedValue);
  }
 else   if ("ALPHA_NUMERIC".equalsIgnoreCase(validationType)) {
    return StringUtils.isAlphanumeric(trimmedValue);
  }
 else   if ("ALPHA".equalsIgnoreCase(validationType)) {
    return StringUtils.isAlpha(trimmedValue);
  }
 else   if ("NUMERIC".equalsIgnoreCase(validationType)) {
    return StringUtils.isNumeric(trimmedValue);
  }
  return true;
}
