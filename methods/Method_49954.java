public static boolean isImageType(String contentType){
  return (null != contentType) && contentType.startsWith("image/");
}
