public static String getMessageWithExceptionHandled(String key){
  if (key == null) {
    return "";
  }
  try {
    return resourceBundle.getString(key).trim();
  }
 catch (  MissingResourceException e) {
    return key;
  }
}
