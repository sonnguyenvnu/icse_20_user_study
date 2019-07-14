public boolean getBool(String key){
  try {
    String value=resourceBundle.getString(key);
    if ("true".equals(value)) {
      return true;
    }
    return false;
  }
 catch (  MissingResourceException e) {
    return false;
  }
}
