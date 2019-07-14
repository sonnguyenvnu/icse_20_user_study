public static boolean isVideoType(String contentType){
  return (null != contentType) && contentType.startsWith("video/");
}
