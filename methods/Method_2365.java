public static int getInt(Object object,Integer defaultValue){
  if (null == object) {
    return defaultValue;
  }
  try {
    return Integer.parseInt(object.toString());
  }
 catch (  Exception e) {
    return defaultValue;
  }
}
