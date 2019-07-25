@Override public boolean validate(String value){
  if (!StringUtils.isEmpty(value)) {
    try {
      parseTimestamp(value);
      return true;
    }
 catch (    IllegalArgumentException e) {
      log.debug("Invalid timestamp format [{}]",value);
      return false;
    }
  }
 else {
    return allowNull;
  }
}
