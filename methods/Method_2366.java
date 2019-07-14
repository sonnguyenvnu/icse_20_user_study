public static boolean getBoolean(Object object,Boolean defaultValue){
  if (null == object) {
    return defaultValue;
  }
  try {
    return Boolean.parseBoolean(object.toString());
  }
 catch (  Exception e) {
    return defaultValue;
  }
}
