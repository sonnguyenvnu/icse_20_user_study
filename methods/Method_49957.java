public static boolean isUnspecified(String contentType){
  return (null != contentType) && contentType.endsWith("*");
}
