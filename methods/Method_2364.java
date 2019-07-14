public static String getString(Object object,String defaultValue){
  if (null == object) {
    return defaultValue;
  }
  try {
    return object.toString();
  }
 catch (  Exception e) {
    return defaultValue;
  }
}
