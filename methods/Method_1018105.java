@Override public boolean validate(String value){
  if (!StringUtils.isEmpty(value)) {
    try {
      parseDate(value);
      return true;
    }
 catch (    IllegalArgumentException e) {
      log.debug("Invalid date format [{}]",value);
      return false;
    }
  }
  return false;
}
