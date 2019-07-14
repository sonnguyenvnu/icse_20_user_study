public static boolean isAudioType(String contentType){
  return (null != contentType) && contentType.startsWith("audio/");
}
