public Integer getInt(String key){
  try {
    String value=resourceBundle.getString(key);
    return Integer.parseInt(value);
  }
 catch (  MissingResourceException e) {
    return null;
  }
}
